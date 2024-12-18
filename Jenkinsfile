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
            environment
            {
                DOCKER_HUB =  credentials('dockerhub-creds')
            }
            steps {
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push dockervsb/selenium_framework"
            }
        }


    }

    post
    {
    always{
      bat "docker logout"
    }
    }


}