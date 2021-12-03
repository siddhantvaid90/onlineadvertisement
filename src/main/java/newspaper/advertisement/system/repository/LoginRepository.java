package newspaper.advertisement.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import newspaper.advertisement.system.model.Login;

public interface LoginRepository extends JpaRepository<Login, String> {

}
