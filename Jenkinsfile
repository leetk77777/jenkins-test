pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Stop Old App') {
            steps {
                bat '''
                @echo off

                for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080" ^| findstr "LISTENING"') do (
                    taskkill /F /PID %%a
                )

                exit /b 0
                '''
            }
        }

        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package'
            }
        }

        stage('Deploy') {
            environment {
                JENKINS_NODE_COOKIE = 'dontKillMe'
            }
            steps {
                bat '''
                @echo off
                cd /d "%WORKSPACE%"

                if exist app.log del /f /q app.log
                if exist app-error.log del /f /q app-error.log

                start "" cmd /c ""%JAVA_HOME%\\bin\\java.exe" -jar "%WORKSPACE%\\target\\jenkins-test-0.0.1-SNAPSHOT.jar" 1>>"%WORKSPACE%\\app.log" 2>>"%WORKSPACE%\\app-error.log""

                timeout /t 5 > nul
                netstat -ano | findstr ":8080"

                exit /b 0
                '''
            }
        }
    }
}