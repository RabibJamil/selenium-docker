pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build Image'){
                    steps{
                        bat "docker build -t=rabib1515/selenium ."
                    }
                }

        stage('Push Image'){
             environment{
                DOCKER_HUB = credentials('dockerhub-creds')
             }
                            steps{
                                bat "docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}"
                                bat "docker push rabib1515/selenium"
                            }
                        }
    }

     post {
            always {
                sh 'docker logout'
            }
        }


}