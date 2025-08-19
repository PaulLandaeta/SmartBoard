pipeline {
    agent any

    environment {
        ANDROID_HOME = '/opt/android-sdk'
        PATH = "${env.PATH}:${env.ANDROID_HOME}/cmdline-tools/latest/bin:${env.ANDROID_HOME}/platform-tools"
        DROPBOX_TOKEN = credentials('DROPBOX_TOKEN')
    }

    stages {
        stage('Prepare local.properties') {
            steps {
                sh 'echo "sdk.dir=${ANDROID_HOME}" > local.properties'
            }
        }

        stage('Accept SDK Licenses') {
            steps {
                sh 'yes | sdkmanager --licenses'
            }
        }

        stage('Install SDK Packages') {
            steps {
                sh '''
                    yes | sdkmanager "platforms;android-34" "build-tools;33.0.1"
                '''
            }
        }

        stage('Clean Gradle Cache') {
            steps {
                sh 'chmod +x ./gradlew'

                sh './gradlew --stop'
                sh 'rm -rf /var/jenkins_home/.gradle/caches/'
                sh 'rm -rf /var/jenkins_home/.gradle/daemon/'
                sh 'rm -rf /var/jenkins_home/.gradle/wrapper/dists/'
            }
        }

        stage('Build APK') {
            steps {
                sh './gradlew assembleDebug'

                sh 'chmod +x ./gradlew'
                sh './gradlew clean'
                sh './gradlew assembleDebug --stacktrace'
            }
        }

        stage('Upload APK to Dropbox') {
            steps {
                script {
                    def apkPath = "app/build/outputs/apk/debug/app-debug.apk"
                    def dropboxDest = "/SmartBoard-UPB/app-debug.apk"
                    sh """
                        curl -X POST https://content.dropboxapi.com/2/files/upload \\
                            --header "Authorization: Bearer $DROPBOX_TOKEN" \\
                            --header "Dropbox-API-Arg: {\\"path\\": \\"${dropboxDest}\\",\\"mode\\":\\"overwrite\\"}" \\
                            --header "Content-Type: application/octet-stream" \\
                            --data-binary @${apkPath}
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Build and upload completed successfully.'
        }
        failure {
            echo 'Build or upload failed.'
        }
    }
}