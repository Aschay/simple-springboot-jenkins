node {
   def commit_id
   stage('Cloning-repo') {
     git branch : 'main',
     url: 'https://github.com/Aschay/simple-springboot-jenkins.git' 
     sh "git rev-parse --short HEAD > .git/commit-id"                        
     commit_id = readFile('.git/commit-id').trim()
   }
   stage('Unit-Testing') {
     def unitApp = docker.image('maven:3.6.3-openjdk-11-slim')
      unitApp.pull()
      unitApp.inside {
       sh 'cd sb-jenkins && mvn clean test'
     }
   }
   stage('Integration-Test') {
     def db = docker.image('mysql:5.7').run(" --name db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=sbdb ") 
     def itApp = docker.image('maven:3.6.3-openjdk-11-slim')
     itApp .pull()
     itApp.inside("--link ${db.id}:mysql") { 
          sh 'cd sb-jenkins  && mvn clean verify -Dspring.profiles.active=test -Dskip.surefire.tests '    
          //add sleep if the database is not ready yet to use wait-for-it script in dockerfile               
     }                                   
     db.stop()
   }                                     
   stage('Build/Push-Docker') {            
     docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
       def app = docker.build("aschay/docker-sb:${commit_id}", '.').push()
     }                                     
  }                                       
}                                          
