package com.study.jong.persistence.ch005.Bi_directional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Member {

    public Member(String id, String username){
        this.id = id;
        this.username = username;
    }

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    // 다 대 일
    @ManyToOne
    @JoinColumn(name= "TEMA_ID")
    private Team team;

    public void setTeam(Team team){
        // 만약삭제하지 않으면 연관관계가 남아 있기 때문
        if(this.team != null){
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.getMembers().add(this);
    }
}
