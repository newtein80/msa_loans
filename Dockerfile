# 1. JDK 17을 포함한 경량 이미지 사용
FROM openjdk:21-jdk-slim

# 2. 작업 디렉토리 설정
WORKDIR /tmp

# 3. JAR 파일을 컨테이너 내부로 복사
COPY build/libs/loans-0.0.1-SNAPSHOT.jar app.jar

# 4. 컨테이너 실행 시 JAR 파일 실행
CMD ["java", "-jar", "app.jar"]