pipeline {
    agent any
    
    environment {
        DOCKER_REGISTRY = 'your-registry'
        APP_NAME = 'sneakpeak-app'
        KUBE_CONFIG = credentials('kubeconfig')
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'cd backend && npm install'
                sh 'cd backend && npm run build'
            }
        }
        
        stage('Test') {
            steps {
                sh 'cd backend && npm test'
            }
        }
        
        stage('Docker Build & Push') {
            steps {
                script {
                    def dockerImage = docker.build("${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER}", "./backend")
                    docker.withRegistry('https://${DOCKER_REGISTRY}', 'docker-credentials') {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                }
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                sh """
                    export KUBECONFIG=\${KUBE_CONFIG}
                    kubectl apply -f k8s/
                    kubectl set image deployment/${APP_NAME} ${APP_NAME}=${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER}
                """
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
    }
} 