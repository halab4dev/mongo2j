pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'java -version'
        sh 'mvn clean install'
      }
    }
    stage('Deploy to Staging') {
      parallel {
        stage('Deploy to Staging') {
          steps {
            sh 'echo "Deploying to Staging"'
          }
        }
        stage('Deploy to Production') {
          steps {
            sh 'echo "Deploy to Production"'
          }
        }
      }
    }
  }
}