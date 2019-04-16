pipeline {
  agent any
  stages {
    stage('Build') {
      tools {
         jdk "jdk-1.8.212"
      }
      steps {
        sh 'cho java -version'
        sh 'mvn clean install'
      }
    }
  }
  environment {
    JAVA_HOME = '/docker-java-home'
  }
}
