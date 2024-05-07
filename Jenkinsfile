pipeline {
    agent any
    environment {
        SERVICE_NAME = sh(
            script: """#!/bin/bash +x
                echo $JOB_NAME | cut -d"/" -f2
            """, 
            returnStdout: true
        ).trim()
        // Extract the service name from the Jenkins job name
        ENV = sh(
            script: """#!/bin/bash +x
                echo $JOB_NAME | cut -d"/" -f1
            """, 
            returnStdout: true
        ).trim()
        // Extract the environment from the Jenkins job name
    }
    stages {
        stage('Checkout sources repository') {
            steps {
                checkout([
                    $class: 'GitSCM', 
                    branches: [[name: "$BRANCH_NAME"]],
                    userRemoteConfigs: [[url: "$GIT_URL"]]
                ])
                // Checkout the source code repository
            }
        }
        // Additional stages can be added here
    }
}
