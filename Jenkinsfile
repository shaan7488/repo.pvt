//Author:"Shanur"
pipeline {
    agent any
    tools {
        maven "maven3.9.2"
    }
    stages {
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }
        stage('SonarQube') {
            steps {
                script {
                    sh 'mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=sample \
                        -Dsonar.projectName="sample" \
                        -Dsonar.host.url=http://207.148.66.46:9000 \
                        -Dsonar.login=sqp_edd07e4851d6518c6d2db30708a5cba6098c412c'
                }
            }
        }
        stage('ssh to remote Server') {
            steps {
                script {
                    sshagent(['SSH_Sever']) {
                        sh "scp -o StrictHostKeyChecking=no target/*.war core@207.148.46.66:/home/core/applications/"
                    }
                }
            }
        }
    }
    post {
    failure {
        script {
            def commitAuthor = sh(script: 'git log -1 --format="%an"', returnStdout: true).trim()
            def commitEmail = sh(script: 'git log -1 --format="%ae"', returnStdout: true).trim()
            def commitTime = sh(script: 'git log -1 --format="%ad"', returnStdout: true).trim()
            def commitHash = sh(script: 'git log -1 --format="%H"', returnStdout: true).trim()
            def buildNumber = env.BUILD_NUMBER
            def jobName = env.JOB_NAME
            def currentDate = new Date().format("yyyy-MM-dd HH:mm:ss")
            def branchName = sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
            def repoUrl = 'https://github.com/shaan7488/hello-world.git'

            def commitInfoText = """
            Job Name       : ${jobName}<br>
            Build Number   : ${buildNumber}<br>
            Commit by      : ${commitAuthor} &lt;${commitEmail}&gt;<br>
            Commit time    : ${commitTime}<br>
            Commit hash    : ${commitHash}<br>
            Current Date   : ${currentDate}<br>
            Branch Name    : ${branchName}<br>
            Git Repository : ${repoUrl}<br>
            """
            emailext body: commitInfoText, compressLog: true, recipientProviders: [buildUser()], subject: 'Build failed', to: 'shanar0004@gmail.com', attachLog: true
        }
    }
    success {
        script {
            def commitAuthor = sh(script: 'git log -1 --format="%an"', returnStdout: true).trim()
            def commitEmail = sh(script: 'git log -1 --format="%ae"', returnStdout: true).trim()
            def commitTime = sh(script: 'git log -1 --format="%ad"', returnStdout: true).trim()
            def commitHash = sh(script: 'git log -1 --format="%H"', returnStdout: true).trim()
            def buildNumber = env.BUILD_NUMBER
            def jobName = env.JOB_NAME
            def currentDate = new Date().format("yyyy-MM-dd HH:mm:ss")
            def branchName = sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
            def repoUrl = 'https://github.com/shaan7488/hello-world.git'

            def commitInfoText = """
            Job Name       : ${jobName}<br>
            Build Number   : ${buildNumber}<br>
            Commit by      : ${commitAuthor} &lt;${commitEmail}&gt;<br>
            Commit time    : ${commitTime}<br>
            Commit hash    : ${commitHash}<br>
            Current Date   : ${currentDate}<br>
            Branch Name    : ${branchName}<br>
            Git Repository : ${repoUrl}<br>
            """

            emailext body: commitInfoText, contentType: 'text/html', recipientProviders: [[$class: 'CulpritsRecipientProvider']], subject: 'Build success', to: 'shanrahman04@gmail.com', attachLog: true
        }
    }
}
}
