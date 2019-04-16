pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'java -version'
        sh 'mvn clean install'
      }
    }
    stage('List files') {
      parallel {
        stage('List files') {
          steps {
            sh 'ls'
          }
        }
        stage('List target file') {
          steps {
            dir(path: './target') {
              sh 'ls'
            }

          }
        }
      }
    }
    stage('Deploy to Staging') {
      steps {
        sh 'echo "Deploy to Staging"'
      }
    }
    stage('Deploy to Production') {
      steps {
        input 'Do you want to deploy to Production?'
        sh 'echo "Deploy to Production"'
      }
    }
  }
}