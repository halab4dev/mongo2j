pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''
          echo $JAVA_HOME
          echo java -version
          echo mvn --version
          mvn clean install
          '''
      }
    }
  }
  environment {
    JAVA_HOME = '/docker-java-home'
  }
}