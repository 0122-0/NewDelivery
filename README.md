# Todo Project

# 🗓️ 개발 기간

- 2025.05.16. 금 - 2025.05.26. 월

# 🛠️ 기술 스택
**언어 및 프레임워크**
- Java 17

**데이터베이스**
- MySQL

**API**
- 구글 스프레드시트

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

 - [ ]  **성능테스트를 위해 대용량 Dummy 데이터 적재하기**
    - **최소 5만건 이상의 데이터를 Insert 할 것 *****
    - **검색에 사용되는 Database Table 에 Dummy 데이터 Insert 하기**

# 📦 캐시(Cache) 적용 이유 및 전략

## 🔍 검색 API에 캐시(@Cacheable)를 적용한 이유

### ✅ 문제 상황
- 리뷰 검색 API는 사용자가 반복적으로 유사한 키워드로 요청을 보내는 기능입니다.
- `LIKE '%keyword%'` 쿼리를 통해 리뷰를 검색하게 되며, 이는 인덱스를 활용하기 어렵고 성능 저하를 유발할 수 있습니다.
- 특히 동일 키워드/페이지 요청이 자주 발생하는 경우 매번 DB를 조회하는 것은 낭비입니다.

### ✅ 캐시 적용 목적
- **중복 요청에 대한 빠른 응답** 제공
- **DB 부하 최소화 및 성능 최적화**
- **API 응답 속도 향상 → 사용자 경험(UX) 개선**

### ✅ 캐시 적용 방식
```java
@Cacheable(value = "reviewSearchCache", key = "#keyword + '_' + #pageable.pageNumber")
public Page<ReviewResponseDto> searchReviewsV2(String keyword, Pageable pageable) { ... }
