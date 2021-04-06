
job('sb-jenkins-build') {
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
        shell ('cd sb-jenkins && chmod +x mvnw  && ./mvnw clean install -DskipTests ')
        dockerBuildAndPublish {
            buildContext("sb-jenkins/")
            repositoryName('aschay/docker-sb')
            tag('v0')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
