package com.study.jong.persistence.ch006;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class OofN {
}

// 하나의 팀은 여러 회원을 참조할수 있지만 회원은 참조하지 않으면 일대다 단방향

@Entity
class TeamOofN{

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn
    private List<MemberOofN> memberOofNS = new ArrayList<>();
}

@Entity
class MemberOofN {

    @Id
    @Column(name = "MEMBER_ID")
    private Long id;
}

/**
 * 단점: 매핑한 객치가 관리하는 외래키가 다른 테이블에 있기때문에 insert를 하기위해 update sql이 한번더 필요
 * 성능상 문제, 관리도 무답
 * 해결: 다대일 양방향 매핑을 사용하는것이 좋다 */

// 어떻게든 일대다 양방향 매핑을 하려면?

@Entity
class TeamOofNBi{

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID") // <- TEAM ID이다
    private List<MemberOofNBi> memberOofNS = new ArrayList<>();
}

@Entity
class MemberOofNBi {

    @Id
    @Column(name = "MEMBER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private TeamOofNBi team;
}