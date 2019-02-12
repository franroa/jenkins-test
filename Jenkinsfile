#!/usr/bin/env groovy

JENKINS_NODE_LABEL = 'dummy-label'


properties([
        buildDiscarder(
                logRotator(
                        numToKeepStr: '30')
        ),
        [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false]
])

stage('SCM checkout') {
    checkout scm
}

stage('Unit Test and package') {
    env.PATH = "${tool 'maven-3.3.9'}/bin:${env.PATH}"
    sh 'make unit-test'
    junit '**/target/surefire-reports/TEST-*.xml'
}


//    TODO

//    Create Docker Image
//
//    Integration Test
//
//    Deploy if I'm on the master


