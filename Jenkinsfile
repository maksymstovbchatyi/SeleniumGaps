pipeline {
    agent any

    stages{
        stage("Build jar"){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }


    }
    post{
        always{
            sh "docker system prune -f"
        }
    }

}