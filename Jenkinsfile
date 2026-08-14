pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Users\\leetk\\AppData\\Local\\Programs\\Eclipse Adoptium\\jdk-21.0.11.10-hotspot'
        DOCKER_HOME = 'C:\\Users\\leetk\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin'

        PATH = "${JAVA_HOME}\\bin;${DOCKER_HOME};${env.PATH}"

        IMAGE_NAME = 'jenkins-test'
        DEPLOYMENT_NAME = 'jenkins-test'
        CONTAINER_NAME = 'jenkins-test'
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }

        stage('Docker Check') {
            steps {
                bat 'docker --version'
            }
        }

        stage('Docker Build') {
            steps {
                bat '''
                @echo off
                cd /d "%WORKSPACE%"
                docker build -t %IMAGE_NAME%:%BUILD_NUMBER% .
                '''
            }
        }

        stage('Kubernetes Check') {
            steps {
                bat '''
                kubectl get nodes
                '''
            }
        }

        stage('Deploy Kubernetes') {
            steps {
                bat '''
                @echo off
                kubectl set image deployment/%DEPLOYMENT_NAME% %CONTAINER_NAME%=%IMAGE_NAME%:%BUILD_NUMBER%
                kubectl set env deployment/%DEPLOYMENT_NAME% SPRING_PROFILES_ACTIVE=dev
                '''
            }
        }

        stage('Check Deployment') {
            steps {
                bat '''
                @echo off
                kubectl rollout status deployment/%DEPLOYMENT_NAME% --timeout=120s
                kubectl get pods
                '''
            }
        }
    }
}