# Todo Project

# 🗓️ 개발 기간

- 2025.05.16. 금 - 2025.05.26. 월

# 🛠️ 기술 스택
**언어 및 프레임워크**
- Java 17

**데이터베이스**
- MySQL

**API**
- Excel

**ERD**
- erdcloud

**API 테스트**
- Postman

## **🔍 요구사항**

- [ ]  **인기 검색어 기능 및 검색기능이 필요한 CRUD Application 기획 및 개발**
    - 어떤 Application 을 개발할지 자유롭게 기획
    - **인기 검색어 기능이 포함되어 있어야한다! (아래 요구사항 참고)**
        - 인기 검색어 조회 API 를 이용해 인기 검색어 목록을 조회할 수 있는 API
    - **단, 검색기능이 포함되어 있어야한다! (아래 요구사항 참고)**
        - 특정 컬럼을 기준으로 `LIKE` 조건을 통해 검색하는 API
            - JPA, QueryDSL 사용가능, 최종적으로 발생하는 SQL 쿼리문이 `LIKE` 구문이 들어가야함
        - Paging 처리를 통해 검색 API 에서 결과를 페이지 단위로 조회
- [ ]  **앞서 개발한 검색 API 에 In-memory Cache(Local Memory Cache) 적용**
    - `spring-boot-starter-cache`  의존성을 이용하고 Spring AOP 방식으로 동작하는 `@Cacheable`  어노테이션을 활용해 구현할 것
    - 기존 API 를 지우는 것이 아닌 새롭게 v2 API 를 추가
    - **총 2개의 검색 API 가 존재해야한다.**
        - v1 API 는 기존에 Cache 가 적용되지 않은 API 이고, (`/api/v1/boards/search` )
        ~~~~기본적으로 v2는 Local Memory Cache가 적용되어야함
      
