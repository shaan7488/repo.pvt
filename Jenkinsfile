pipeline {
    agent any
    
    stages {
        stage('Checkout sources repository') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: "$BRANCH_NAME"]], userRemoteConfigs: [[url: "$GIT_URL"]]])
            }
        }
    }
}
