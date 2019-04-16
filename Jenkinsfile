pipeline {
  agent any
  tools {
    maven 'Maven 3.3.9'
    jdk 'jdk8'
  }
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
}
