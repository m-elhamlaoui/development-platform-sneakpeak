pipeline {
  agent any

  tools {
    jdk 'JDK21'
  }

  environment {
    JAVA_HOME          = "${tool 'JDK21'}"
    PATH               = "${env.JAVA_HOME}/bin:${env.PATH}"
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

    stage('Build & Test') {
      steps {
        dir('backend') {
          sh 'chmod +x mvnw'
          sh './mvnw clean package -B'
        }
        junit 'backend/target/surefire-reports/*.xml'
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
