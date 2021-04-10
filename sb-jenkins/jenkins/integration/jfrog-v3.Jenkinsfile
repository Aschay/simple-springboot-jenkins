pipeline {
    agent any

    stages {
        stage ('Clone') {
            steps {
                git branch: 'master', url: 'https://github.com/jfrog/project-examples.git'
            }
        }

        stage ('Upload file') {
            steps {
                rtUpload (
                    // Obtain an Artifactory server instance, defined in Jenkins --> Manage Jenkins --> Configure System:
                    serverId: "aschay.jfrog.io",
                    spec: """{
                            "files": [
                                    {
                                        "pattern": "jenkins-examples/pipeline-examples/resources/ArtifactoryPipeline.zip",
                                        "target": "test-libs-snapshot-local"
                                    }
                                ]
                            }"""
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

        stage ('Set-output-resources') {
            steps {
                jfPipelines(
                    outputResources: """[
                        {
                            "name": "pipelinesBuildInfo",
                            "content": {
                                "buildName": "${env.JOB_NAME}",
                                "buildNumber": "${env.BUILD_NUMBER}"
                            }
                        }
                    ]"""
                )
            }
        }
    }
}