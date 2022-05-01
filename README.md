# 포토그램 - 인스타그램 클론 코딩

### STS 툴 버그가 발견되었습니다.
- 아래 주소로 가서 4.0.6 버전으로 설치해주세요. 아니면 의존성 다운로드 79프로에서 무한루프가 발생합니다.
- https://github.com/spring-projects/sts4/wiki/Previous-Versions

### STS 툴에 세팅하기 - 플러그인 설정
- https://blog.naver.com/getinthere/222322821611

### 의존성

- Spring Boot DevTools
- Lombok
- Spring Data JPA
- MariaDB Driver
- Spring Security
- Spring Web
- oauth2-client

```xml
<!-- 시큐리티 태그 라이브러리 -->
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-taglibs</artifactId>
</dependency>

<!-- JSP 템플릿 엔진 -->
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>tomcat-jasper</artifactId>
	<version>9.0.43</version>
</dependency>

<!-- JSTL -->
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
</dependency>
```

### 데이터베이스

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database photogram;
```

### yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/cos?serverTimezone=Asia/Seoul
    username: cos
    password: cos1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
      
  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB

  security:
    user:
      name: test
      password: 1234   

file:
  path: C:/src/springbootwork-sts/upload/
```

### 태그라이브러리

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
```


### http 정리
- 스프링 부트는 서블릿으로 만들어졌다.
- 요청의 종류가 3개이면 3개의 JAVA 파일이 필요하다.
- 너무 많은 요청이 한곳으로 모이는 것을 방지하기 위해서 도메인 별로 분기다.
- 분기의 일은 Dispatcher가 해준다.
- 스프링은 Dispather가 만들어져있다.

<h3>HTTP 4가지 요청 방식 </h3>
- 클라이언트가 웹서버에 요청
- 웹서버는 DB에 SELECT, INSERT, UPDATE, DELETE 요청을 해서 응답
- GET - 데이터 요청
- POST - 데이터 전송 (http 바디가 필요하다)
- PUT 데이터 갱신 (바디 필요)
- DELETE - 데이터 삭제

HTTP BODY에는 데이터가 담기기 때문에 데이터를 수정하려면 BODY가 필요하다.


<h3> HTTP 쿼리스트링(querystring), 주소 변수 매핑(path variable) </h3>
1. 구체적인 데이터 요청 시에 쿼리스트링이나 주소 변수 매핑이 필요하다.
2. 스프링부트에서는 주소변수매핑을 주로 사용한다. 훨씬 편리

<h3>http body 데이터 전송하기</h3>

- http header의 Content-Type 이해 (post, put 타입)
- 스프링부트는 기본적으노 x-www-form-urpencoded 타입을 파싱(분석)해준다.
- x-www-form-urlencoded // key = value
- plain/text 안녕
- application/json {"username" : "cos"}


<h3>http 요청을 file로 응답하기</h3>

1. .txt파일 응답하기 (기본 경로는 resources/static)
2. 스프링 부트가 지원하는 .mustache 파일 응답하기
3. 스프링 부트가 버린 .jsp 파일 응답하기

- .jsp와 .mustache 파일은 템플릿 엔진을 가지고 있다.
- 템플릿 엔진이란 html 파일에 java 코드를 쓸 수 있는 친구들이다.

### 전처리와 후처리
- 전처리는 앞단에서 오류가 나서 롤백하는것 (validation 유효성 검사)
- 예) 제약조건 username이 중복
- 후처리는 서버 JPA DB 테이블까지 들어와서 오류가 나서 띄워주는 것이 후처리(exceptionhandler)
- 예) 제야조건 length가 너무 길다던지..
- 이러한 것들을 공통기능이라고 함 없어도 회원가입은 잘 돌아가지만 있어야 더 잘돌아가게 된다.
- 이런걸 AOP 관점지향프로그래밍라고 한다.

### 구독 API 구현하기

1. 연관관계 개념
- 1: N의 관계에서 FK는 Many가 가져간다.
- N : N의 관계에서는 중간 테이블이 생긴다.
- 중간테이블과의 관계는 항상 1: N : 1의 관계다

