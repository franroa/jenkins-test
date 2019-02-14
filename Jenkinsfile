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
                sh 'ls'
//                sh 'make test'
            }
        }

        stage('Publish') {
            when {
                expression {
                    return isRunningMaster()
                }
            }
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
    }
}

boolean isRunningMaster() {
    return env.BRANCH_NAME == 'master'
}