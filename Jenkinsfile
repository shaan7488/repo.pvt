pipeline {
    agent any
    
    stages {
        stage('Checkout sources repository') {
            steps {
                script {
                    // Dynamically retrieve the branch name from the environment
                    def branchName = env.BRANCH_NAME ?: 'master'
                    
                    // Checkout the repository using the retrieved branch name
                    checkout([$class: 'GitSCM', branches: [[name: branchName]], userRemoteConfigs: [[url: "$GIT_URL"]]])
                }
            }
        }
    }
}
