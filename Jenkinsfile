pipeline {
  agent any

  environment {
    REGISTRY        = 'docker.io/babayas'
    IMAGE           = 'development-platform-sneakpeak/sneaky-backend'
    GIT_CREDENTIALS = 'git-pwd'
    DOCKER_CREDS    = 'docker-hub'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout([
          $class: 'GitSCM',
          branches: [[name: 'origin/news-devops']],
          userRemoteConfigs: [[
            url           : 'https://github.com/m-elhamlaoui/development-platform-sneakpeak.git',
            credentialsId : env.GIT_CREDENTIALS
          ]]
        ])
      }
    }

    stage('Build') {
      steps {
        dir('backend') {
          sh 'chmod +x mvnw'
          sh './mvnw clean package -B'
        }
      }
    }

    stage('Docker Login') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: env.DOCKER_CREDS,
          usernameVariable: 'DOCKER_USER',
          passwordVariable: 'DOCKER_PASS'
        )]) {
          sh '''
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
          '''
        }
      }
    }

    stage('Build & Push Docker Image') {
      steps {
        dir('backend') {
          script {
            // Build
            sh """
              docker build \
                --file Dockerfile \
                --tag ${env.REGISTRY}/${env.IMAGE}:${env.BUILD_NUMBER} .
            """
            // Push
            sh "docker push ${env.REGISTRY}/${env.IMAGE}:${env.BUILD_NUMBER}"
          }
        }
      }
    }
  }

  post {
    success { echo '✅ CI pipeline completed successfully.' }
    failure { echo '❌ CI pipeline failed.' }
  }
}
