#!/usr/bin/env groovy

node {
    stages {
        stage('Build') {
            checkout scm
        }

        stage('Unit/Integration Test') {
            sh 'make test'
        }

        if (isRunningMaster()) {
            stage('Publish') {
                sh 'make docker'
            }
        }
    }
}

boolean isRunningMaster() {
    return env.BRANCH_NAME == 'master'
}