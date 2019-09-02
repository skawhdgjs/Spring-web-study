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


JPQL 특징 

* JPQL은 객체지향 쿼리 언어이다. 따라서 테이블을 대상으로 쿼리하는것이 아니라 엔티티 객체를 대상으로 쿼리한다.
* JPQL은 SQL을 추상화해서 특정 데이터베이스 SQL에 의존하지 않는다.
* JPQL은 결국 SQL로 변환된다.

1. Select 문

- 엔티티와 속성은 대소문자를 구분한다. 
- 클래스명이 아니라 엔티티명을 사용한다.
- 별칭은 필수로 사용해야한다 (As 문법)

쿼리 객체

TypeQuery, Query - 작성한 JPQL을 실행하려면 쿼리 객체를 만들어야 한다.

* 반환할 객체를 정확히 지정할 수 있ㅇ면 *TypeQuery* 아니라면 *Query*를 사용하면 된다. 

em.createQuery()의 두 번쨰 파라미터에 반환할 타입을 지정하면 TypeQuey를 반환하고 지정하지 않으면 Query를 반환한다.
조회 대상이 Member 엔티티이므로 조회 대상 타입이 명확하다.

### 결과 조회

query.getResultList(): 결과를 예제로 반환한다.

query.getSingleResult(): 결과가 정확히 하나일 때 사용한다.
 - 결과가 없으면 NoResultExcpetion, 1개보다 많으면 NoUniqueResultException 예외가 발생한다.
 
파라미터 바인딩

JDBC는 위치 기준 파라미터 바인딩만 지원하지만 JPQL은 이름 기준 파라미터 바인딩도 지원한다.
(이름 기준 파라미터는 앞에 :를 사용한다.)

위치 기준 파라미터 ( ?1 ) 같이 사용

* 이름 기준 파라미터 바인딩 방식을 사용하는것이 더 명확하다.


### 프로젝션

Select 절에 조회할 대상을 지정하는 것을 프로젝션이라 하고 (Select { 프로젝션 } from ) 프로젝션 대상은 *엔티티*, *임베디드 타입*, *스칼라 타입*


##### 엔티티 프로젝트
```sql
SELECT m from Member m;
SELECT m.team from Member m;

```
객체를 바로 조회한것 ( 칼럼을 하나하나 나열하는게 아님 ) 
영속성 컨텍스트에서 관리됨

#### 임베디드 타입 프로젝션
 
JPQL에서 임베디드 타입은 엔티티와 거의 비슷하게 사용된다. 임베디드 타입은 조회의 시작점이 될 수 없다는 제약이 있다.

Address 가 order에 포함된 임베디드 타입이라면 oder엔티티를 시작점으로 해야한다.
```java
String query = "SELECT o.address FROM Order o"
List<Address> addresses = em.createQuery(query, Adddress.class).getResultList();
```

*임베디드 타입은 엔티티 타입이 아닌 값 타입이다. 따라서 이렇게 직접 조회한 임베디드 타입은 영속성 컨텍스트에서 관리되지 않는다.*

#### 스칼라 타입 프로젝션

숫자, 문자, 날짜와 같은 기본 데이터 타입들을 스칼라 타입이라고 함.
```java
List<String> username = em.createQuery("SELECT username from Member m", String.class)
.getResultList();
```

- 통계도 주로 스칼라 타입으로 한다.

여러 값 조회 

엔티티를 대상으로 조회하지 않을떄, 꼭 필요한 데이터들만 선택해서 조회

프로젝션에 여러값을 선택하면 TypeQuery를 사용할 수 없고 대신에 Query를 사용해야 한다.

```java
Query query = em.createQuery("SELECT m.username m.age FROM Member m")
List resultList =query.getReulstList();

이터레이터 ->
```