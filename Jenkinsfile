
pipeline{
       agent any            
        tools{
             maven 'maven'
             }

             stages {
                   stage('Verify Branch'){
                   steps{
                           echo "@GIT_BRANCH"



                        }
                      }
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
                  stage('Package'){
                   steps{
                           bat 'mvn package'
                        }
                     }

                           stage('Test'){
                            steps{
                                    bat 'mvn test'
                                }
                             }





               }
        }
