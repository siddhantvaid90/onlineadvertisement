package newspaper.advertisement.system.jwt;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import newspaper.advertisement.system.exception.InvalidTokenException;
import newspaper.advertisement.system.model.*;

@Service
public class JwtTokenUtil implements Serializable {
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

	@Value("${jwt.secret}")
	private String secretKey;

	// generate token for user
	public String generateToken(AppUser user) {

		Claims claims = Jwts.claims();
		
		claims.setSubject(user.getEmail());
		claims.put("user", user.getEmail());

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}

	//generate token for RTOOFFICER
	public String generateAdminToken(Login admin) {

		Claims claims = Jwts.claims();
		claims.setSubject(admin.getEmail());
		claims.put("admin", admin.getEmail());

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
	
	public AppUser validateTokenAndGetUserDetails(HttpServletRequest request) {
		final String tokenHeader = request.getHeader("Authorization");

		String token = null;

		if (tokenHeader == null)
			throw new InvalidTokenException("User Not Logged In or token not included");
		
		//Method to remove Bearer from JWT Token
		if (!tokenHeader.startsWith("Bearer "))
			throw new InvalidTokenException("Invalid Token");

		token = tokenHeader.substring(7);
		
		try {
			Claims claims =Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			AppUser user = new AppUser();
			user.setEmail(claims.getSubject());
			//user.setRole((String) claims.get("role"));
			return user;
		} catch (SignatureException ex) {
			throw new InvalidTokenException("Token Signature not valid");
			
		} catch (ExpiredJwtException ex) {
			throw new InvalidTokenException("Token expired. Login again");
		}
		catch (IllegalArgumentException | MalformedJwtException ex) {
			throw new InvalidTokenException("Token is invalid");
		}
	}
	
	/*public RTOOfficer validateTokenAndGetOfficerDetails(HttpServletRequest request) {
		final String tokenHeader = request.getHeader("Authorization");

		String token = null;

		if (tokenHeader == null)
			throw new InvalidTokenException("User Not Logged In or token not included");
		
		//Method to remove Bearer from JWT Token
		if (!tokenHeader.startsWith("Bearer "))
			throw new InvalidTokenException("Invalid Token");

		token = tokenHeader.substring(7);
		
		try {
			Claims claims =Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			RTOOfficer rtoofficer = new RTOOfficer();
			rtoofficer.setUsername(claims.getSubject());
			return rtoofficer;
		} catch (SignatureException ex) {
			throw new InvalidTokenException("Token Signature not valid");
			
		} catch (ExpiredJwtException ex) {
			throw new InvalidTokenException("Token expired. Login again");
		}
		catch (IllegalArgumentException | MalformedJwtException ex) {
			throw new InvalidTokenException("Token is invalid");
		}
	}*/

	public boolean validateToken(String token) {
		// TODO Auto-generated method stub
		return false;
	}
}
