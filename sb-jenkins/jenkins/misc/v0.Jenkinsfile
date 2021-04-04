pipeline {
  agent any
  tools {
    maven 'M3'
  }
  stages {
    stage('Cloning-repo') {
      steps {
        git branch: 'main', url: 'https://github.com/Aschay/simple-springboot-jenkins.git'
      }
    }
    stage('Building') {
      steps {
        sh "cd sb-jenkins && mvn package -DskipTests"
      }
    }
    stage('Test') {
      steps {
        echo "Test Stage"
        sh "cd sb-jenkins && mvn test "
      }
    }
  }
}