pipeline {
  /* ─── Use a small Alpine image with docker CLI baked in ─── */
  agent {
    docker {
      image 'docker:24.0.5'                     // official Docker client image
      args  '-v /var/run/docker.sock:/var/run/docker.sock'
    }
  }

  tools {
    jdk 'JDK21'                                // name of your JDK installation in Jenkins > Global Tool Config
  }

  environment {
    /* point JAVA_HOME to the tool and add it to PATH */
    JAVA_HOME = "${tool 'JDK21'}"
    PATH      = "${env.JAVA_HOME}/bin:${env.PATH}"

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
        /* now `docker` DSL is available because we're in the docker:24… image */
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
