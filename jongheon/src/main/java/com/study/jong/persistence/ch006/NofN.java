package com.study.jong.persistence.ch006;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 * 관계형 데이터베이스는 정규화된 테이블2개로 다대다 관계를 표현할수 없다.
 * 그래서 보통 다대다 테이블을 일대다, 다대일 관계로 풀어내는 연결 테이블을 사용
 * 그러나 객체는 객체 2개로 다대다 관계를 만들 수 있다.
 */


public class NofN {


}

@Entity
class MemberNofN{

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    // name : 연결 테이블 지정, joinColumns: 현재 방향인 회원과 매핑할 조인 칼럼 정보를 지정, inverseJoinColumns: 반대 방향인 상품과 매핑할 조인 칼럼 정보를 지정
    private List<Product> products = new ArrayList<>();

}


@Entity
class Product{
    @Id @Column(name = "PRODUCT_ID")
    private String id;
}


@Entity
class ProductBi{
    @Id @Column(name = "PRODUCT_ID")
    private String id;

    @ManyToMany(mappedBy = "producsts")
    private List<MemberNofN> members;
}

// 만약에 연결테이블에 주문 수량 칼럼이나 주문한 날짜 칼럼이 들어가면 어쩔수 없이 연겵테이블 엔터티를 만들어야 함
@Entity
class MemberAdd {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    @OneToMany(mappedBy = "memberAdd")
    private List<MemberProduct> memberProducts;
}

@Entity
class ProductAdd{
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;
}

@Entity
@IdClass(MemberProductId.class)
// ㄴ 기본키가 MEBMER_ID, PRODUCT_ID로 이루어진 복합키 이다. 식별관계!
class MemberProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberAdd memberAdd;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductAdd productAdd;

    private int orderAmount;
}

/***
 * JPA에서 복합키를 사용하려면 별도의 식별자 클래스를 만들어야 한다 그리고 엔티티에 @IdClass를 이용하애한다.
 *
 * 복합키를 위한 식별자 클래스
 * 1.serializable 구현
 * 2. equals, hashcode ( 대부분 자동 생성 )
 * 3. 기본 생성자
 * 4. public
 * 5. @IdClass외에 @EmbeddedID를 사용하는 방법도 있음
 */
@EqualsAndHashCode
@Setter
@Getter
class MemberProductId implements Serializable {
    private String member;
    private String product;
}
/***
 * 복합키를 사용하지 않고 쉽게 사용하는 방법
 *
 * 데이터베이스에서 자동으로 생성하ㅐ주는 대리키를 Long값으로 사용하는 것이다.
 * 비식별 관계 : 받아온 식별자는 외래키로만 사용하고 새로운 식별자를 추가한다
*/

@Entity
class Order{

    // 이것!!
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberAdd memberAdd;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductAdd productAdd;

    private int orderAmount;
}