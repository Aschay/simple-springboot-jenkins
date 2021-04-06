//check this file for sonarqube build 
// https://github.com/Aschay/simple-nodejs-jenkins/blob/f46cdfe1a58a77bcc9fcb1bea3cdde1e435681c3/jenkins/integrations/sonar-docker-compose.yaml
node {
     def mavenInstallation = 'M3'
  stage('cloning-repo') {
        git branch: 'main' , 
            url: 'https://github.com/Aschay/simple-springboot-jenkins.git'
  }
  stage('Building') {
       withEnv( ["PATH+MAVEN=${tool mavenInstallation}/bin"] ) {  
	          sh 'cd sb-jenkins &&  mvn clean package -DskiptTests  '
        }
	}
  stage('code-quality-check') {
      def sonarqubeScannerHome = tool name: 'sonar', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
      withCredentials([string(credentialsId: 'sonar', variable: 'sonarLogin')]) {
        sh "${sonarqubeScannerHome}/bin/sonar-scanner \
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
  stage('Unit-testing') {
       withEnv( ["PATH+MAVEN=${tool mavenInstallation}/bin"] ) {  
	          sh 'cd sb-jenkins &&  mvn clean test '
      }
	}
}
