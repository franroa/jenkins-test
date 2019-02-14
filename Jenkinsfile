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

        when {
            expression {
                return isRunningMaster()
            }
        }

        stage('Publish') {
            steps {
                sh 'make docker'
            }
        }
    }
}

boolean isRunningMaster() {
    return env.BRANCH_NAME == 'master'
}