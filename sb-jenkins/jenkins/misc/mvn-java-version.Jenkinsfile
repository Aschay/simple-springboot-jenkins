pipeline {
  agent any
   tools {
       maven "M3"
       } 
    stages {
        stage('Test-version') {
            steps {
               sh ' echo "what tools are being used " '
               sh 'mvn --version'  // this should input prefered installed java version for maven  
               sh 'java -version'  // this should input default jre installed with jenkins
            }
      }
    }
}
