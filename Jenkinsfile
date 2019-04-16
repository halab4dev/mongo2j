pipeline {
  agent any
  stages {
    stage('Build') {
      tools {
         jdk "JDK 8"
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
