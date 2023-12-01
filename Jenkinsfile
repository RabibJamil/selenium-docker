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
                                bat 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                                bat "docker push rabib1515/selenium"
                            }
                        }
    }

    post{

        always{
            bat "docker logout"
        }

    }


}