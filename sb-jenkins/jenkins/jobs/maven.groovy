
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
    steps {
        shell( "cd sb-jenkins")
        maven {
            goals('clean')
            goals('package')
            rootPOM("sb-jenkins/pom.xml")
            properties(skipTests: true)
            mavenInstallation('M3')
        }
    }
}
