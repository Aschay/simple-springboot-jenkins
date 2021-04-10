pipeline {
    agent any
    stages {
        stage ('Clone') {
            steps {
                git branch: 'main', url: "https://github.com/Aschay/simple-springboot-jenkins.git"
            }
        }

        stage ('Artifactory-configuration') {
            steps {
                rtServer (
                    id: "aschay.jfrog.io",
                )

                rtMavenDeployer (
                    id: "MAVEN_DEPLOYER",
                    serverId: "aschay.jfrog.io",
                    releaseRepo: "test-libs-release-local",
                    snapshotRepo: "test-libs-snapshot-local"
                )

                rtMavenResolver (
                    id: "MAVEN_RESOLVER",
                    serverId: "aschay.jfrog.io",
                    releaseRepo: "test-libs-release",
                    snapshotRepo: "test-libs-snapshot"
                )
            }
        }

        stage ('Building') {
            steps {
                rtMavenRun (
                    tool: 'M3', // Tool name from Jenkins configuration
                    pom: 'sb-jenkins/pom.xml',
                    goals: 'clean package -DskipTests',
                    deployerId: "MAVEN_DEPLOYER",
                    resolverId: "MAVEN_RESOLVER"
                )
            }
        }

        stage ('Publish-build-info') {
            steps {
                rtPublishBuildInfo (
                    serverId: "aschay.jfrog.io"
                )
            }
        }
    }
}