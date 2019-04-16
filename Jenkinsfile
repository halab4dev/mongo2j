pipeline {
  agent any
  stages {
    stage('Build') {
      tools {
         jdk "jdk-1.8.212"
      }
      steps {
        sh 'echo java -version'
        sh 'mvn clean install'
      }
    }
  }
  environment {
    JAVA_HOME = '/docker-java-home'
  }
}
