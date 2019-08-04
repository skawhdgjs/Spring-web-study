package com.study.jong.persistence.ch005.Bi_directional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Team {

    public Team(String id, String name){
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    // 일 대 다
    // 양방향 매핑일때 mappedBy속성, 반대족 매핑의 필드 이름을 값으로 주면 된다 ( Member.team )
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
