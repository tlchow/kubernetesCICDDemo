pipeline {
    environment {
        DEPLOY = "${env.BRANCH_NAME == "master" || env.BRANCH_NAME == "develop" ? "true" : "false"}"
        NAME = "${env.BRANCH_NAME == "master" ? "example" : "example-staging"}"
        VERSION = readMavenPom().getVersion()
        DOMAIN = 'localhost'
        REGISTRY = 'tlchow/k8sdemo'
        REGISTRY_CREDENTIAL = 'dockerHub'
    }
    agent {
        kubernetes {
            defaultContainer 'jnlp'
            yamlFile 'build.yaml'
        }
    }
    stages {
        stage('Build') {
            steps {
			/*
				echo 'skip Build'
			*/
                container('maven') {
                    sh 'mvn package'
                }
            }
        }
        stage('Docker Build') {
            when {
                environment name: 'DEPLOY', value: 'true'
            }
            steps {
				/*
				echo 'skip Docker Build'
				*/
                container('docker') {
					def customImage = docker.build(" ${REGISTRY}:${VERSION}")				
                }
            }
        }
        stage('Docker Publish') {
            when {
                environment name: 'DEPLOY', value: 'true'
            }
            steps {
				/*
				echo 'skip Docker Publish'
                container('docker') {
                    withDockerRegistry([credentialsId: "${REGISTRY_CREDENTIAL}", url: "https://docker-registry.default.svc.cluster.local:5000"]) {
                        sh "docker push ${REGISTRY}:${VERSION}"
                    }
                }
				*/
                container('docker') {
					docker.withRegistry([credentialsId: "${REGISTRY_CREDENTIAL}", url: ""]) {
						customImage.push()
					}
				}
            }
        }
        stage('Kubernetes Deploy') {
            when {
                environment name: 'DEPLOY', value: 'true'
            }
            steps {
                container('helm') {
                    sh "helm upgrade --install --namespace default --set name=${NAME} --set image.tag=${VERSION} --set domain=${DOMAIN} ${NAME} ./helm "
                }
            }
        }
    }
}