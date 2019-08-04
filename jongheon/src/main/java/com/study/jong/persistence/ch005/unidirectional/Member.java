package com.study.jong.persistence.ch005.unidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.beans.ConstructorProperties;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @Column(name = "USER_ID")
    private String id;
    private String username;

    public Member(String id, String username){
        this.id = id;
        this.username = username;
    }


    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // ㄴ optional = ture(flase로 설정하면 연관된 엔터티가 항상 있어야 한다)
    @JoinColumn(name = "TEAM_ID")
    // ㄴ 매핑할 외래 키 이름, 생략 될 수 있음 [ 생략되면 team_TEAM_ID ]
    private Team team;
}
