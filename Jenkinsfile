pipeline {
    agent any

    stages {
        stage("Build jar") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage("Build docker") {
            steps {
                sh "docker build -t=goldengros/selenium ."
            }
        }

        stage("Push image") {
            environment{
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps {
                sh 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
                sh "docker push goldengros/selenium"
            }
        }
    }

    post {
        always {
           sh "docker logout"
        }
    }
}
