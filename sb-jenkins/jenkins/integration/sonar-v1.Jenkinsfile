pipeline {
agent any
  tools {
    maven 'M3'
  }
stages {
 stage("cloning-repo") {
      steps {
        script {
          git branch: 'main', url: 'https://github.com/Aschay/simple-nodejs-jenkins.git' 
          }
       }
  }
   stage("Build") {
   steps {
        sh "cd sb-jenkins &&  mvn clean package -DskiptTests  "
       }
   }
  stage('code-quality-check') {
    steps {
       script {
       def scanner = tool name: 'sonar', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
        withCredentials([string(credentialsId: 'sonar', variable: 'sonarLogin')]) {
           sh "${scanner}/bin/sonar-scanner \
            -e -Dsonar.host.url=http://ip-here/ \
               -Dsonar.login=${sonarLogin} \
               -Dsonar.projectName=sb-jenkins \
               -Dsonar.projectVersion=${env.BUILD_NUMBER} \
               -Dsonar.java.binaries=sb-jenkins/. \
               -Dsonar.sources=sb-jenkins/src/main/ \
               -Dsonar.tests=sb-jenkins/src/test/  \
               -Dsonar.projectKey=sb-jenkins \
               -Dsonar.language=java "
               }
           }
       }
  }
  stage('Unit-testing') {
      steps {
	      sh 'cd sb-jenkins &&  mvn clean test '
      }
	}   
 }
}