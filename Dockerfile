FROM openjdk:8-jdk-alpine
EXPOSE 8090
ADD /target/kubernetesCICDDemo*.jar kubernetesCICDDemo.jar
ENTRYPOINT ["java", "-jar", "kubernetesCICDDemo.jar"]
