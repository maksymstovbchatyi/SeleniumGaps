pipeline {
    agent any

    environment {
        DOCKER_HUB = credentials('dockerhub-creds')
    }

    stages {
        stage("Build jar") {
            steps {
                script {
                    docker.image('maven:3.8.1-jdk-11').inside {
                        sh "mvn clean package -DskipTests"
                    }
                }
            }
        }

        stage("Build docker") {
            steps {
                sh "docker build -t=goldengros/selenium ."
            }
        }

        stage("Push image") {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-creds') {
                        sh "docker push goldengros/selenium"
                    }
                }
            }
        }
    }

    post {
        always {
            sh "docker system prune -f"
        }
    }
}
