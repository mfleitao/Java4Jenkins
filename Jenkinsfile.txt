pipeline {
	agent any

	stages {
		stage('Build') {
			steps {
				whithMaven(maven: 'maven_3_6_1') {
					echo 'Building...'
					bat 'mvn clean compile'
					archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
				}
			}
		}
		stage('Test') {
			steps {
				whithMaven(maven: 'maven_3_6_1') {
					echo 'Testing...'
					bat 'mvn test'
					archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
				}
			}
		}
	}
}