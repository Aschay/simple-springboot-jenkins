pipeline {
    agent {
        docker {
            image 'maven:3.6.3-openjdk-11-slim'
        }
   }
    stages {
        stage('Getting-Information') {
            steps {
               sh 'mvn --version'
            }
      }
       stage('Cloning-repo') {
            steps {
               git branch: 'main', url: 'https://github.com/Aschay/simple-springboot-jenkins.git'
            }
      }
      stage('Build') {
            steps {
                sh 'cd sb-jenkins && mvn clean package -DskipTests '
            }
        }
      stage('Test') {
            steps {
               sh 'cd sb-jenkins && mvn test'
            }
      }
    }
}
