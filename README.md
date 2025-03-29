# 🥄 Fooding - Backend

식재료 공유 및 교환 플랫폼 **Fooding**의 Backend Repository입니다.

## 📌 프로젝트 소개
**Fooding**은 지역 기반으로 식재료를 나누고 교환할 수 있는 서비스입니다.  
불필요하게 버려지는 식재료를 서로 나누고, 필요한 사람들과 효율적으로 연결될 수 있도록 돕습니다.

## 🚀 기술 스택

| 구분      | 스택                                  |
|---------|------------------------------------|
| Language | Java 17                          |
| Framework | Spring Boot 3.x, Spring Data JPA |
| Database | AWS RDS (MySQL)                  |
| Infra    | AWS EC2, Nginx, Certbot(SSL)     |
| 기타     | JWT 인증, S3 파일 업로드, Redis (추후 채팅 기능 확장 가능성) |

## 🗂️ 프로젝트 구조

```
fooding-backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── store.fooding.backend
│   │   │       ├── controller     # API 컨트롤러
│   │   │       ├── dto            # 요청, 응답 DTO
│   │   │       ├── model          # Entity
│   │   │       ├── repository     # JPA Repository
│   │   │       ├── service        # 비즈니스 로직
│   │   │       └── config         # 설정
│   │   └── resources
│   │       └── application.yml    # 환경설정
│   └── test
│       └── ...
└── build.gradle
```

## ⚙️ 환경 변수 (.env or 환경 설정)
> 서버 실행 시 반드시 필요한 환경 변수입니다.


| 변수명                      | 설명                               |
|----------------------------|------------------------------------|
| DATASOURCE_URL             | MySQL 데이터베이스 URL            |
| DATASOURCE_USERNAME        | MySQL 데이터베이스 사용자 이름    |
| DATASOURCE_PASSWORD        | MySQL 데이터베이스 비밀번호      |
| AWS_ACCESS_KEY             | AWS S3 Access Key                 |
| AWS_SECRET_KEY             | AWS S3 Secret Key                 |
| AWS_S3_BUCKET_NAME         | S3 버킷 이름                     ||

> **배포 환경에서는 `.env` 파일 또는 EC2 환경변수에 등록되어 있음**

## 🛠️ 주요 기능

- **회원가입 & 로그인 (JWT)**
- **식재료 등록, 수정, 삭제, 조회**
- **카테고리별 조회**
- **사용자별 등록 식재료 조회**
- **식재료 이미지 S3 업로드**
- **JWT 인증 기반 API 보호**
- **식재료 거래 상태 관리**

## 🖥️ 배포 구조

![image](https://github.com/user-attachments/assets/b12a9372-3ffc-4734-b994-518e363bbe7c)


## ERD

![image](https://github.com/user-attachments/assets/9dad5c7b-8302-4855-a15f-15a9a185a16d)




## 📄 API 명세서

👉 추후 Swagger 연동 예정  


## ⚡️ 실행 방법

```bash
# 1. 환경변수 등록
export DATASOURCE_URL=jdbc:mysql://your-rds-url:3306/fooding
export DATASOURCE_USERNAME=your-username
export DATASOURCE_PASSWORD=your-password
export AWS_ACCESS_KEY=your-access-key
export AWS_SECRET_KEY=your-secret-key
export AWS_S3_BUCKET_NAME=your-bucket
...

# 2. 빌드 & 실행
./gradlew build
java -jar build/libs/Backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

## 👨🏻‍💻 개발자

- **김민석**  
  (팀원 정보는 원하면 추가해 드릴 수 있습니다)

