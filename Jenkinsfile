#!/usr/bin/env groovy

JENKINS_NODE_LABEL = 'dummy-label'

node(JENKINS_NODE_LABEL) {

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


    // TODO

//    stage('Create Docker Image') {
//        withStagingCredentials({
//            env.PATH = "${tool 'maven-3.3.9'}/bin:${env.PATH}"
//            sh 'make docker-image'
//        })
//    }
//
//    stage('Integration Test') {
//        env.PATH = "${tool 'maven-3.3.9'}/bin:${env.PATH}"
//        sh 'make integration-test'
//        junit '**/target/surefire-reports/TEST-*.xml'
//    }


//  Deploy if I'm on the master


}
