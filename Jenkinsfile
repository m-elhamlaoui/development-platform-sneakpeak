pipeline {
  agent any

  environment {
    // point the build at your system’s Java 21 install:
    JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
    PATH      = "${env.JAVA_HOME}/bin:${env.PATH}"

    REGISTRY        = 'docker.io/babayas'
    IMAGE           = 'development-platform-sneakpeak/sneaky-backend'
    GIT_CREDENTIALS = 'git-pwd'
    DOCKER_CREDS    = 'docker-hub'
  }

  stages {
    stage('Checkout') {
      steps {
        // completely clear out any old files before pulling
        cleanWs()

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
      agent {
        docker {
          image 'docker:24.0.5'
          args  '-v /var/run/docker.sock:/var/run/docker.sock'
        }
      }
      steps {
        script {
          docker.withRegistry('', env.DOCKER_CREDS) {
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
