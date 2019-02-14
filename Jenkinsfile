#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                checkout scm
            }
        }

        stage('Unit/Integration Test') {
            steps {
                sh 'make test'
            }
        }

        script {
            if (isRunningMaster()) {
                stage('Package') {
                    steps {
                        parallel(
                                a: {
                                    sh 'make docker'
                                },
                                b: {
                                    sh 'tree'
                                }
                        )
                    }
                }

//            stage('Publish staging image') {
//                steps {
//                    withStagingCredentials {
//                        awsNonProduction.publishImage(utils.getVersion())
//                    }
//                }
//            }
//
//            stage('Publish production image') {
//                steps {
//                    withProductionCredentials {
//                        awsNonProduction.publishImage(utils.getVersion())
//                    }
//                }
//            }
            }
        }
    }
}

boolean isRunningMaster() {
    return env.BRANCH_NAME == 'master'
}