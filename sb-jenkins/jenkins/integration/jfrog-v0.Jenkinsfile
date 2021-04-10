node {
    def server
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo

    stage ('Cloning-repo') {
         git branch : 'main',
     url: 'https://github.com/Aschay/simple-springboot-jenkins.git' 
    }

    stage ('Artifactory-configuration') {
        server = Artifactory.server "aschay.jfrog.io"
        rtMaven.tool = "M3"
        rtMaven.deployer releaseRepo: "test-libs-release-local", snapshotRepo: "test-libs-snapshot-local", server: server
        rtMaven.resolver releaseRepo: "test-libs-release", snapshotRepo: "test-libs-snapshot ", server: server
        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Exec-Maven') {
        rtMaven.run pom: 'sb-jenkins/pom.xml', goals: 'clean package -DskipTests ', buildInfo: buildInfo
    }

    stage ('Publish-build-info') {
        server.publishBuildInfo buildInfo
    }
}