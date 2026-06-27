pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Users\\leetk\\AppData\\Local\\Programs\\Eclipse Adoptium\\jdk-21.0.11.10-hotspot'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package'
            }
        }

        stage('Run') {
            steps {
                bat '''
                for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080') do taskkill /F /PID %%a
                start /B java -jar target\\*.jar
                '''
            }
        }
    }
}