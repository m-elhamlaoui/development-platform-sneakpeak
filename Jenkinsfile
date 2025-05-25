pipeline {
  agent any

  environment {
    REGISTRY           = 'docker.io/babayas'
    IMAGE              = 'development-platform-sneakpeak/sneaky-backend'
    GIT_CREDENTIALS    = 'git-pwd'
    DOCKER_CREDENTIALS = 'docker-hub'
  }

  tools {
    jdk 'JDK21'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout([
          $class: 'GitSCM',
          branches: [[name: 'news-devops']],
          userRemoteConfigs: [[
            url: 'https://github.com/m-elhamlaoui/development-platform-sneakpeak.git',
            credentialsId: env.GIT_CREDENTIALS
          ]]
        ])
      }
    }

    stage('Build') {
      steps {
        dir('backend') {
          sh 'chmod +x mvnw'
          // runs compile, tests, packaging in one go
          sh './mvnw clean package -B'
        }
      }
    }

    stage('Build & Push Docker Image') {
      steps {
        dir('backend') {
          withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS,
                                            usernameVariable: 'DOCKER_USER',
                                            passwordVariable: 'DOCKER_PASS')]) {
            sh '''
              echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
              docker build -t $REGISTRY/$IMAGE:$BUILD_NUMBER .
              docker push $REGISTRY/$IMAGE:$BUILD_NUMBER
            '''
          }
        }
      }
    }
  }

  post {
    success {
      echo '✅ CI pipeline completed successfully.'
    }
    failure {
      echo '❌ CI pipeline failed.'
    }
  }
}
