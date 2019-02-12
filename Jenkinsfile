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
            steps {
                parallel("first": {
                    withMaven(maven: 'maven-3.3.9') {
                        sh 'make unit-test'
                    }
                },
                        "second": {
                            echo "world"
                        }
                )
            }

        }


        stage('Deploy') {
            steps {
                //
            }
        }
    }
}
