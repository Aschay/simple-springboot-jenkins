node {
    def server
    def rtMaven
    def buildInfo

    stage ('Cloning-repo') {
           git branch : 'main',
               url: 'https://github.com/Aschay/simple-springboot-jenkins.git' 
    }

    stage ('Artifactory-configuration') {
        server = Artifactory.server "aschay.jfrog.io"
        rtMaven = Artifactory.newMavenBuild()
        rtMaven.tool = "M3" // Tool name from Jenkins configuration
        rtMaven.deployer releaseRepo: "test-libs-release-local", snapshotRepo: "test-libs-snapshot-local", server: server
        rtMaven.resolver releaseRepo: "test-libs-release", snapshotRepo: "test-libs-snapshot ", server: server
        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Building') {
        docker.image('maven:3.6.3-openjdk-11-slim').inside {
            withEnv(['JAVA_HOME=/usr/local/openjdk-11']) { 
                rtMaven.run pom: 'sb-jenkins/pom.xml', goals: 'clean package -DskipTests', buildInfo: buildInfo
            }
        }
    }

    stage ('Publishing-build-info') {
        server.publishBuildInfo buildInfo
    }
}