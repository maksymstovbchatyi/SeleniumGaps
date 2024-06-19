pipeline {
    agent any

    stages{
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
                   steps {
                       sh "docker push goldengros/selenium"
                   }

    }
    post{
        always{
            sh "docker system prune -f"
        }
    }

}