pipeline {
    agent any
    environment {
        REGISTRY = "k8s-vga-worker1:5000"
        IMAGE_NAME = "group1-team2-loan-app"
        IMAGE_TAG = "latest"
        NAMESPACE = "group1-team2"
	JAVA_HOME = "/usr/local/java21"
	PATH = "${JAVA_HOME}/bin:$PATH"
    }
    stages {
        stage('Checkout') {
            steps {
                // Git 저장소에서 소스 코드 체크아웃 (branch 지정 : 본인 repository의 branch 이름으로 설정)
                git branch: 'main', url: 'https://github.com/newtein80/msa_loans.git'
            }
        }
        stage('Build with Gradle') {
            steps {
                script {
                    // Gradle 실행권한
                    sh 'chmod +x ./gradlew'
                    // Gradle 빌드 실행
                    sh './gradlew clean build'
                    // Maven 빌드 실행 (위 두줄 주석처리 및 아래 한줄 주석해제)
                    // sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    // Docker 이미지를 Registry Server에 푸시
                    sh "docker push ${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Kubernetes Deployment and Service 생성 및 적용 (1일차 교육때 사용한 deploy & service 생성 yaml 파일 등록하여 사용)
                    sh "kubectl apply -f ./yaml/loan-deployment.yaml -n ${NAMESPACE}"
                }
            }
        }
        stage('Service to Kubernetes') {
            steps {
                script {
                    // Kubernetes Deployment and Service 생성 및 적용 (1일차 교육때 사용한 deploy & service 생성 yaml 파일 등록하여 사용)
                    sh "kubectl apply -f ./yaml/loan-service.yaml -n ${NAMESPACE}"
                }
            }
        }
        stage('Deployment Image to Update') {
            steps {
                script {
                    // Kubenetes에서 특정 Deployment의 컨테이너 이미지를 업데이트 (아래 이름은 중복되지 않게 주의하여 지정, deployment, selector 이름으로)
                    sh "kubectl set image deployment/deploy-loan-app container-loan-app=${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} --namespace=${NAMESPACE}"
                }
            }
        }
    }
}