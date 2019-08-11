package com.study.jong.persistence.ch009;

import javax.persistence.*;
import java.util.Date;

public class JpaValueType {
}

@Entity
class basicType {
    @Id
    private Long id;

    // 값 타입 ( 식별자 값도 없고 생명주기도 회원 엔티티에 의존한다, 절대 공유 되지 않는다.
    private String name;
    // 값 타입
    private int age;
}

// 임베디드 타입 : 새로운 값 타입을 직접 정의
@Entity
class EmbeddedType{
    @Id
    private Long id;

    @Embedded Period workPeriod;
    @Embedded Address homeAddress;

}

@Embeddable
class Period {

    @Temporal(TemporalType.DATE) Date startDate;
    @Temporal(TemporalType.DATE) Date endDate;
}

@Embeddable
// 임베디드 타입을 포함한 모든 값 타입은 엔티티의 생명주기에 의존 하므로 엔티티와 임베디드 타입의 관계는 컴포지션 관게
class Address {

    @Column(name = "city")
    private String city;

    @ManyToOne
    PhoneServiceProvider provider;
}

@Entity
class PhoneServiceProvider {
    @Id
    private String name;
}

// @AttributeOverride

@Entity
class MemberOver {
    @Id
    private Long id;

    @Embedded
    Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY"))
    })
    Address compnayAddress;
}

