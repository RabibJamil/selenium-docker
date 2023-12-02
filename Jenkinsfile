/*
    Note:

    Windows users use "bat" instead of "sh"
    For ex: bat 'docker build -t=rabib1515/selenium .'

*/
pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image'){
            steps{
                sh 'docker build -t=rabib1515/selenium .'
            }
        }

        stage('Push Image'){
            environment{
                // assuming you have stored the credentials with this name
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push rabib1515/selenium'
            }
        }

    }

    post {
        always {
            sh 'docker logout'
        }
    }

}