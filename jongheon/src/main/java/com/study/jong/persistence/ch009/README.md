## 값 타입

JPA 데이터 타입을 크게 분류하면 엔티티 타입과 값 타입으로 나눌 수 있다.

엔티티 타입: @Entity로 정의하는 객체

값 타입: Int, Integer, String 처럼 단순히 값 자바 기본 타입, 객체

엔티티 타입은 식별자를 통해 지속적으로 추적할 수 있지만, 값 타입은 식별자가 없고 숫자나 문자같은 속석만 있으므로 추적할 수 없다.
(객체는 값 타입은 단순히 수치 정보)

기본값 타입
- 자바 기본 타입
- 래퍼 클래스
- String

임베디드 타입 ( 복합 값 타입 , JPA에서 사용자가 직접 정의한 값타입)

컬렉션 값 타입 ( 하나 이상의 값 타입을 저장할 때 사용)

#### 기본값타입

-- 기본

#### 임베디드 타입

직접 작성한 타입, @Embedded, @Embeddable로 작성

임베디드 타이븐 인티티의 값일 뿐이다. 따라서 값이 속한 엔티티의 테이블에 매핑한다.
예제에서 임베디드 타입을 사용하기 전과 후에 매핑하는 테이블은 같다.

#### 임베디드 타입관 연관관계

임베디드 타입은 값 타입을 포함하거나 엔티티르 참조할 수 있다.

#### @AttributeOverride

임베디드 타입에 정의한 매핑정보를 재정의 하려면 엔티티에 @AttributeOverride를 사용하면 된다.

! 무조건 엔티티에 설정해야한다.

임베디드 타입이 null 이면 매핑한 칼럼 값은 모두 null이 된다.

#### 값 타입과 불변 객변 객체

임베디드 타입 같은 값타입을 여러 엔티티에서 공유하면 위험하다.

공유 참조로 인핸 발생하는 버그 -> 부작용을 만으려면 값을 복사해서 사용하면 된다.

#### 값 타입 복사

값 타입의 실제 인스턴스인 값을 공유하는 것은 위험하다. 대신에 값을 복사해서 사용해야한다.

```java
member1.setHomeAddress(new Address("Oldcity"))
Address address = member1.getHomeAddress();

Address newAddress = address.clone();
newAddress.setCity("Newcity")
member2.setHoeAddress(newAddress); 
```

위처럼 문제는 복사하지 않고 원본읜 참조 값을 직접 넘기는 것을 막을 방법이 없다.

!객체를 불변하게 만들면 값을 수정할 수 없으므로 부작용을 완전 차단할 수 있다.

* 따라서 값 타입은 될 수 있으면 불변 객체로 설계해야한다.

가장 쉬운 방법은 생성자로만 값을 설정하고 수정자를 만들지 않으면 된다. (Setter 없이)

불변이라는 작은제약으로 큰 부작용을 막을 수 있다.

#### 값 타입의 비교

자바 동일성 비교: 인스턴스 참조 값을 비교

자바 동등성 비교: 인스턴스 값 비

값 타입의 equlas()메소드를 재정의 할때는 보통 모든 필드의 값을 비교하도록 구현한다.
(equals를 재정하면 hashCode를 재정의하는 것이 안전 그렇지 않으면 해시를 사용하는 컬렉션이 정상 작동하지 않는다.)

### 값 타입 컬렉션

값 타입을 하나 이상 저장하려면 컬렉션에 보관하려면
@ElementCollection, @CollectionTable 어노테이션을 사용하면 된다.

데이터베이스 테이블로 매핑해야 하는데 관계형 데이터베이스의 테이블은 커럼안에 컬렌션을 포함할 수없다.
따라서 별도의 테이블을 추가하고 @CollectionTable를 사용해서 추가한 테이블을 매핑해한다.
그리고 사용되는 컬럼이 하나면 @Column을 사용해서 컬럼며을 지정할 수 있다.
(@CollectionTable을 생략하면 기본값을 사용해서 매핑한다 - {엔티티이름_컬렉션 속성이름} )

### 값 타입 컬렉션 사
```java
Member member = new Member();
member.setHomeAddres(new Address());
member.getFavoriteFoods().add("과일1")
member.getFavoriteFoods().add("fruit2")
member.getFavoriteFoods().add("fruit3")

em.persist(member);
```
마지막에 member 엔티티만 영속화 했다. JPA는 이때 member 엔티티의 값 타입도 함께 저장한다.
실제 데이터베이스에서는 member 한번 homeAddress는 컬렉션이 아닌 임베디드 값 타입이므로 회원테이블을 저장하는 SQL에 포함된다.
member.favoriteFood Insert sql3번

값 타입 컬렌션도 조회할 떄 페치 전략을 선택할 수 있는데 기본은 LAZY이다.

> 임베디드 값 타입 수정

``
Member member = em.find(Member.class, 1L);
member.setHomeAddress(new Address);
``

-> homeAddress의 임베디드 값타입은 MEMBER테이블과 매핑했으므로 MEMBER테이블만 UPDATE한다. 

``
Set<String> favoriteFoods = member.getFavoriteFoods();
favoriteFoods.remove("fruit1")
favoriteFoods.add("fruit2")
``

fruit1을 fruit2로 변경하려면 먼저 제거고 추가해야한다.

```java
List<Address> addressHistory = member.getAddressHistory();
addressHistory.remove(new Address());
addressHistory.add(new Address());
```

값 타입은 불변해야한다. 따라서 컬렉션에서 기존 주소를 삭제하고 새롱누 주소를 등록했다.

> 값 타입 컬렉션의 제약사항

엔티티는 식별자가 있으므로 엔티티의 값을 변경해도 식별자로 데이터 베이스에 저장된 원본 데이터를 쉽게 찾아서 변경 가능.
반면에 값 타입은 식별자x가 아니므로 값이 변경되면 원본 데이터를 찾기 어렵다.
특정 엔티티 하나에 소속된 값 타입은 값이 변경되어도 자신이 소속된 엔티티를 데이터베이스에서 찾고 값을 변경하면 되지만
문제는**값 타입 컬렉션이다**.

값 타입 컬렉션에 보관된 값 타입들은 별도의 테이블에 보고나된다. 따라서 여기에 보관된 값 타입의 값이 변경되면
데이터베이스에 있는 원본 데이터를 찾기 어렵다는 문제가 있다. 이런 문제로 인해 JPA 구현체들은 값 타입 컬렉션에 변경 사항이 
발생하면, 값 타입 컬렉션이 매핑된 테이블의 연관되 모든 데이터를 삭제하고, 현재 값 타입 컬렉션 객체에 있는 모든 값을 데이터 베이스에
다시 저장한다.
예를들어 식별자가 100번인 회원이 관리하는 주소 값 타입 컬렉션을 변경하면 회원 100번과 관련되 주소 데이터를 삭제하고
현재 값 타입 컬렉션에 있는 값을 다시 저장한다. 따라서 값 타입 컬렉션이 매핑된 테이블에 데이터가 많다면 값 타입
컬렉션 대신에 일대다 관게를 고려해야한다.

### 정리

엔티티 타입과 값 타입

1. 엔티티 타입
    * 식별자가 있다.
    * 생명주기가 있다.
    * 영속 및 제거
    * 공유할 수 있다.
    
2. 값 타입 
    * 식별자가 없다.
    * 생명주기를 엔티티에 의존한다.
    * 공유하지 않는 것이 안전하다. 