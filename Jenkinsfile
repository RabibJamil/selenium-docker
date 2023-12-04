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
                        sh 'docker build -t=rabib1515/selenium:latest .'
                    }
                }

        stage('Push Image'){
             environment{
                DOCKER_HUB = credentials('dockerhub-creds')
             }
                            steps{
                                sh "docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}"
                                sh 'docker push rabib1515/selenium:latest'
                                sh "docker tag rabib1515/selenium:latest rabib1515/selenium:${env.BUILD_NUMBER}"
                                sh "docker push rabib1515/selenium:${env.BUILD_NUMBER}"
                            }
                        }
    }

    post {
                always {
                sh 'docker logout'
            }
        }


}