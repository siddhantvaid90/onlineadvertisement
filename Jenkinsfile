pipeline{
agent{label 'master'}
    tools{maven 'M3'}


		stages {
			stage('Verify Branch'){
			steps{
			echo "@GIT_BRANCH"
			
			}
			}
			stages{
                  stage('Checkout'){
               steps{
                git branch: 'main', url: 'https://github.com/siddhantvaid90/onlineadvertisement.git'
            }
        }

			stage('Build'){
            steps{
                bat 'mvn compile'
            }
        }
        stage('Test'){
            steps{
                bat 'mvn test'
            }
        }
        stage('Package'){
            steps{
                bat 'mvn package'
            }
		

	                }
}
