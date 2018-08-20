kotlin-webflux-swagger

# 설정
  - Project Language Level : 1.8
  - Intellij 설정 : Project Compiler output = /build
  
  
# 설치
 - monogo db

 ```
 $ brew install mongodb
 $ mongod --dbpath ~/mongo/db
 ```

# 테스트
 - http://localhost:7070/api
 - swagger : http://localhost:7070/swagger-ui.html#/post-controller
 
# 참고
 - webflux 동작 방식 :  https://github.com/arawn/building-serverless-application-with-spring-webflux/blob/master/README.md
 - Responsive Spring(https://www.youtube.com/watch?v=UIrwrW5A2co)과 Spring Webflux(https://www.youtube.com/watch?v=2E_1yb8iLKk) 차이와 선택 고민하기