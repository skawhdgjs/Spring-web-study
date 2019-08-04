package com.study.jong.persistence.ch006;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class NofO {


}

// 단방향

@Entity
class Member{

    @Id
    @Column(name = "USER_ID")
    private Long id;
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

}

@Entity
class Team{

    @Id
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;
}

// 양방향


@Entity
@Getter
class MemberBi{

    @Id
    @Column(name = "USER_ID")
    private Long id;
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private TeamBi team;

    public void setTeam(TeamBi team){
        this.team = team;

        // 무한 루프에 빠지지 않도록
        if(!team.getMemberBiList().contains(this)){
            team.getMemberBiList().add(this);
        }
    }

}

@Entity
class TeamBi{

    @Id
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @Getter
    @OneToMany(mappedBy = "team")
    private List<MemberBi> memberBiList = new ArrayList<>();

    public void addMember(MemberBi memberBi){
        this.memberBiList.add(memberBi);
        // 무한 루프 금지
        if(memberBi.getTeam() != this){
            memberBi.setTeam(this);
        }
    }
}