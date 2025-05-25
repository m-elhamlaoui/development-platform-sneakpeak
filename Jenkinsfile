pipeline {
  /* ─── Run inside Docker CLI image so we can build & push images ─── */
  agent {
    docker {
      image 'docker:24.0.5'
      args  '-v /var/run/docker.sock:/var/run/docker.sock'
    }
  }

  environment {
    REGISTRY           = 'docker.io/babayas'
    IMAGE              = 'development-platform-sneakpeak/sneaky-backend'
    GIT_CREDENTIALS    = 'git-pwd'
    DOCKER_CREDENTIALS = 'docker-hub'
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

    stage('Build & Push Docker Image') {
      steps {
        script {
          docker.withRegistry('', env.DOCKER_CREDENTIALS) {
            def img = docker.build("${env.REGISTRY}/${env.IMAGE}:${env.BUILD_NUMBER}", './backend')
            img.push()
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
