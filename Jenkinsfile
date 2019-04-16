pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'java -version'
        sh 'mvn clean install'
      }
    }
  }
}
