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
            // 젠킨스가 빌드 종료 후 프로세스를 죽이지 않도록 설정
            environment {
                JENKINS_NODE_COOKIE = 'dontKillMe'
            }
            steps {
                bat '''
                @echo off
                cd /d "%WORKSPACE%"
		
                :: 8080 포트를 사용 중인 기존 프로세스 종료
                for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080" ^| findstr "LISTENING"') do (
                    taskkill /F /PID %%a
                )
		
                :: 백그라운드에서 자바 실행 (JENKINS_NODE_COOKIE 덕분에 유지됨)
                start "" /B "%JAVA_HOME%\\bin\\java.exe" -jar "%WORKSPACE%\\target\\jenkins-test-0.0.1-SNAPSHOT.jar" > "%WORKSPACE%\\app.log" 2>&1
		
                :: 5초 대기 후 포트 정상 작동 확인
                timeout /t 5 > nul
                netstat -ano | findstr ":8080"
		
                exit /b 0
                '''
            }
        }
    }
}