#!/usr/bin/env groovy


//    TODO

//    Create Docker Image
//
//    Integration Test
//
//    Deploy if I'm on the master


pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                checkout scm
            }
        }

        stage('Unit Test and package') {
            env.PATH = "${tool 'maven-3.3.9'}/bin:${env.PATH}"
            sh 'make unit-test'
            junit '**/target/surefire-reports/TEST-*.xml'
        }


        stage('Deploy') {
            steps {
                //
            }
        }
    }
}
