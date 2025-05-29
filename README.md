# 📝 미니 프로젝트 01: To-Do List

> 개인별 할 일을 날짜 기반으로 관리할 수 있는 웹 애플리케이션

---

## ✅ 기능 요구 사항 정리

### 1. 사용자 기능 (인증/인가)

- 회원 가입 / 로그인 / 로그아웃
- 로그인한 사용자만 자신의 To-Do를 확인, 작성, 수정, 삭제 가능
- 사용자마다 **개인 달력**을 가짐

### 2. To-Do 기능

- 할 일 등록, 수정, 삭제
- 할 일 정보:
  - **내용 (필수)**
  - **완료 여부 (체크박스)**
  - **날짜 (yyyy-MM-dd 형식)**

### 3. 날짜 및 달력 기능

- 달력 UI 제공
- 날짜 클릭 시 해당 날짜의 To-Do 목록 표시
- 이전/다음 날로 이동하는 기능 지원

---

## 🛠️ 기술 스택

| 항목             | 내용                   |
|------------------|------------------------|
| 백엔드 프레임워크 | Spring Boot 3.5.0      |
| 언어             | Java 17                |
| 빌드 도구        | Gradle                 |
| 템플릿 엔진       | Thymeleaf              |
| DB (개발용)       | H2 Database            |
| ORM              | Spring Data JPA        |
| 인증             | Spring Security        |
| IDE              | IntelliJ IDEA          |

---
## ERD

<img src="https://github.com/user-attachments/assets/9b3bcb7f-9c09-4235-b2d6-a32e77cf77a9" width="500" />

