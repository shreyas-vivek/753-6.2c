pipeline {

    agent any

 

    environment {

        DIRECTORY_PATH = "/path/to/code/directory"

        TESTING_ENVIRONMENT = "TestingEnv"

        PRODUCTION_ENVIRONMENT = "YourNameProdEnv"

    }

 

    stages {

        stage('Build6') {

            steps {

                echo "Fetching the source code from the directory path: ${env.DIRECTORY_PATH}"

                echo "Compiling the code and generating artifacts"

            }

        }

 

        stage('Test') {

            steps {

                script {

                    echo "Running unit tests"

                    echo "Running integration tests"


                }

            }

            post {

 

                always {

 

                  emailext attachLog: true,

 

                  body: 'Test pipeline has been successfully executed',

 

                  subject: 'Test is Successful',

 

                  to: 'shreyasvivek01@gmail.com'

 

                }

 

            }

        }

 

        stage('Code Quality Check') {

            steps {

                script {

                    echo "Checking the quality of the code in the pipeline"


                }

            }

        }

 

        stage('Deploy') {

            steps {

                script {

                    echo "Deploying the application to ${env.TESTING_ENVIRONMENT}"

                    // Add your deployment steps for testing environment here

                }

            }

        }

 

        stage('Approval') {

            steps {

                script {

                    echo "Waiting for manual approval..."

                    // Add manual approval steps here

                }

            }

        }

 

        stage('Deploy to Production') {

            steps {

                script {

                    echo "Deploying the code to the production environment: ${env.PRODUCTION_ENVIRONMENT}"

                    // Add your deployment steps for production environment here

                }

            }

        }

    }

 

    post {

        success {

            echo "Pipeline executed successfully"

        }

        failure {

            echo "Pipeline execution failed"

        }

    }

}