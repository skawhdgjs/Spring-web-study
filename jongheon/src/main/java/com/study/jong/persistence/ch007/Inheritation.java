package com.study.jong.persistence.ch007;

import javax.persistence.*;

public class Inheritation {
}

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// ㄴ 상속매핑은 부모 클래스에 위 어노테이션을 사용 , 조인 전략
@DiscriminatorColumn(name = "DTYPE")
// ㄴ 부모 클래스에 구분 칼럼을 지정, 이칼럼으로 저장된 자식 테이블을 구분할 수 있다.
abstract class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
}

@Entity
@DiscriminatorValue("A")
// ㄴ 엔터티를 저장할때 구분 칼럼에 입력할 값
class Album extends Item {

    private String artist;
}

@Entity
@DiscriminatorValue("B")
class Movie extends Item {
    private String director;
    private String actor;
}

@Entity
@DiscriminatorValue("C")
@PrimaryKeyJoinColumn(name = "BOOK_ID")
// ㄴ 부모의 기본키 칼럼명을 변경하고 싶을때 사용
class Book extends Item {

}