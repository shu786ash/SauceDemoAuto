pipeline{
	agent any
	tools{
		maven 'Maven'
		jdk 'JDK21'
	}
	stages{
		stage('Clone'){
			steps{
				git "https://github.com/shu786ash/SauceDemoAuto.git"
				
			}
		}
		stage('Build'){
			steps{
				bat 'mvn clean compile'
			}
		}
		stage('Test'){
			steps{
				bat 'mvn test'
			}
		}
		stage('Publish Report'){
			steps{
				publishHTML([
					allowMissing:true,
					alwaysLinkToLastBuild:true,
					keepAll:true,
					reportDir:'test-output',
					reportFiles:'index.html',
					reportName:'TestNG Report'
				])
			}
		}
	}
	post{
		always{
			archiveArtifacts artifacts:'test-output/**',
			allowEmptyArchive:true
		}
	}
}
