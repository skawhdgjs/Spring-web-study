package com.study.jong.persistence.ch006;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

public class OofO {
}

/**
 *
 * 일대일 관계는 그 반대도 일대일
 * 테이블 관계에서 일대일 관계는 주 테이블이나 대상테이블 중 어느곳이나 외래키를 가질 수 있다.
 *
 * 주 테이블에 외래키 ( 객체 지향 개발자들이 선호)
 * 대상 테이블에 외래키 ( 전통적인 데이터베이스 개바자 들이 선호, 일대일에서 일대다로 변경할때 테이블 구조를 그대로 유지할 수 있다
 */

// 1. 주 테이블에 외래키 단방향
@Entity
class MemberOofO {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
}

@Entity
class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

}

// 1. 주 테이블에 외래키 양방향
@Entity
class MemberOofOBi {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private LockerBi locker;
}

@Entity
class LockerBi {

    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    @OneToOne(mappedBy = "MEMBER_ID")
    private MemberOofOBi memberOofOBi;

}

// 2. 대상 테이블에 외래키 - 단방향은 허용하지 않는다.
@Entity
class MemberOfoOother {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToOne(mappedBy = "member")
    private LockerOfoBiother locker;
}

class LockerOfoBiother{
    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberOfoOother memberOfoOother;
}
