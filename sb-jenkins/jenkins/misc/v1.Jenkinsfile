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
    stage('Build-App') {
      steps {
        sh "cd sb-jenkins && mvn package -DskipTests"
      }
    }
    stage('Unit-Test') {
      steps {
        echo "Unit Test Stage"
        sh "cd sb-jenkins && mvn test "
      }
    }
     stage('Integration Test') {
      steps {
        echo "Test Stage"
        sh "cd sb-jenkins && mvn verify -Dskip.surefire.tests"
      }
    }
    stage('Deliver') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './sb-jenkins/jenkins/scripts/deliver.sh'
            }
    }
  }
}