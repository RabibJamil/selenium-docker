pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image'){
                    steps{
                        bat 'docker build -t=rabib1515/selenium:latest .'
                    }
                }

        stage('Push Image'){
             environment{
                DOCKER_HUB = credentials('dockerhub-creds')
             }
                            steps{
                                bat 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
                                bat 'docker push rabib1515/selenium:latest'
                                bat "docker tag rabib1515/selenium:latest rabib1515/selenium:${env.BUILD_NUMBER}"
                                bat "docker push rabib1515/selenium:${env.BUILD_NUMBER}"
                            }
                        }
    }

    post {
                always {
                bat 'docker logout'
            }
        }


}