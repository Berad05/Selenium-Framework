pipeline {
     agent any

    stages {
        stage('Build Jar') {
            steps {

                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Docker Image') {
            steps {

                bat "docker build -t=dockervsb/selenium_framework ."
            }
        }
        stage('push Image') {

            steps {

                bat "docker push dockervsb/selenium_framework"
            }
        }


    }

}