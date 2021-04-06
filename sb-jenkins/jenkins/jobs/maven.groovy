
job('simple-sb-jenkins-build') {
    scm {
        git('https://github.com/Aschay/simple-springboot-jenkins.git') { 
            node -> 
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        maven ('maven') 
         
    }
    steps {
        shell( "cd sb-jenkins && mvn clean package -DskipTests ")
    }
}
