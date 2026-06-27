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

        stage('Deploy') {
		    steps {
		        bat '''
		        @echo off
		        cd /d "%WORKSPACE%"
		
		        for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080" ^| findstr "LISTENING"') do (
		            taskkill /F /PID %%a
		        )
		
		        powershell -Command "Start-Process java -ArgumentList '-jar target\\jenkins-test-0.0.1-SNAPSHOT.jar' -WorkingDirectory '%WORKSPACE%' -RedirectStandardOutput 'app.log' -RedirectStandardError 'app-error.log'"
		
		        timeout /t 5 > nul
		        netstat -ano | findstr ":8080"
		
		        exit /b 0
		        '''
		    }
		}
    }
}