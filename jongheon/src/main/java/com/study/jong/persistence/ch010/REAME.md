## 객체 지향 쿼리

* 객체지향 쿼리 소개
* JPQL
* Criteria
* QueryDSL
* 네이티브 SQL
* 객체지향 쿼리 심화

> JPA는 복잡한 검색 조건을 사용해서 엔티티 객체를 조ㅗ히할 수 있는 다양한 쿼리 기술을 지원한다.
JPQL은 가장 중요한 객체지향 쿼리 언어 , Criteria, QueryDSL은 JPQL을 편리하게 사용하도록 도와주는 기술이다.


#### 객체 지향 쿼리 소개

ORM을 사용하면 엔티티 객체를 대상으로 개발하므로 검색도 테이블이 아닌 엔티티 객체를 대상으로 하는 방법이 필요하다

* 테이블이 아닌 개체를 대상으로 검색하는 객체지향 쿼리
* SQL을 추상화해서 특정 데이터베이스 SQL에 의존하지 않는다.

(SQL: 데이터베이스 테이블, JPQL: 엔티티 객체를 대상으로 하는 테이블)

JPA가 공식 지원하는 기능

1. JPQL
2. Criteria 쿼리
3. Native SQL

공식지원은 아니지만 알아둘 가치

* QueryDSL(지금은 지원하는것 같음)
* JDBC MyBaits 직접 사용

### JPQL 소개

JPQL은 엔티티 객체를 조회하는 객체지향 쿼리.
문법은 SQL과 비슷하고 ANSI 표준 SQL이 제공하는 기는을 유사하게 지원한다.
JPQL은 SQL을 추상화해서 특정 데이터베이스에 의존하지 않는다.

JPQL은 SQL보다 간결하다.

ㄴ 엔티티티 직접 조회, 묵시적 조인, 다형성 지원

#### Criteria
JPQL을 작성할 수 있다. 대신 복잡하다.

#### QueryDsl

1. 코드 기반
2. 단순

#### 네이티브 SQL

SQL은 지원하지만 JPQL이 지원하지 않는 기능이 있을떄 쓴다.

#### JDBC 커넨션에 직접 접근

JPA는 JDBC 커넥션을 흭득하는 API를 제공하지 않으므로 JPA구현체가 제공하는 방법을 사용해야 한다.

```java
Session session = eentityManager.unwray(Session.class);
session.doWork(new Work(){
    public void execute(Connection connection)
})
``` 


1. JPA EntityManager에서 하이버네이트 Session을 구한다. 그리고 Session의 doWork() 메소드를 호출하면 된다.
2. JDBC나 마이바티스를 JPA와 하꼐 사용하면 영속성 컨텍스트를 적절한 시점에서 강제로 플러시 해야한다.
모두 JPA를 우회해서 데이터베이스에 접근하기 때문에 JPA가 인식하지 못한다. 
3. JPA를 우회해서 SQL을 실행하기 직전에 영속성 컨텍스트를 수동으로 플러시해서 데이터베이스와 영속성 컨텍스트를 동기화 하면 된다.
(스프링 프레임워크에서 AOP를 적절히 활요하면 JPA를 우회하여 데이터베이스에 접근하는 메소드를 호출할때마다 영속성 컨텍스트를 플러시하면 
위에서 언급한 문제도 깔끔하게 해결할 수 있다.)

### JPQL