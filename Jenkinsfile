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
		    environment {
		        JENKINS_NODE_COOKIE = 'dontKillMe'
		    }
		    steps {
		        bat '''
		        @echo off
		        cd /d "%WORKSPACE%"
		
		        for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080" ^| findstr "LISTENING"') do (
		            taskkill /F /PID %%a
		        )
		
		        start "" cmd /c ""%JAVA_HOME%\\bin\\java.exe" -jar "%WORKSPACE%\\target\\jenkins-test-0.0.1-SNAPSHOT.jar" > "%WORKSPACE%\\app.log" 2>&1"
		
		        timeout /t 5 > nul
		        netstat -ano | findstr ":8080"
		
		        exit /b 0
		        '''
		    }
		}
    }
}