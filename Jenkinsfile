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
                sh 'make docker'
            }
        }
    }
}

boolean isRunningMaster() {
    return env.BRANCH_NAME == 'master'
}