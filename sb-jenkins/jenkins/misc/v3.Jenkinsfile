//scripted jenkinsfile 
node {
   def mavenInstallation = 'M3'
   def commit_id

   stage('Cloning-repo') {
     git branch : 'main',
     url: 'https://github.com/Aschay/simple-springboot-jenkins.git' 
     sh "git rev-parse --short HEAD > .git/commit-id"                        
     commit_id = readFile('.git/commit-id').trim()
   }
   stage('Building') {
       withEnv( ["PATH+MAVEN=${tool mavenInstallation}/bin"] ) {  
	          sh 'cd sb-jenkins &&  mvn clean package -DskiptTests  '
           }
	}
   stage('Docker-build/push') {
     docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
       def app = docker.build("aschay/docker-sb:${commit_id}", './sb-jenkins').push()
     }
   }
}