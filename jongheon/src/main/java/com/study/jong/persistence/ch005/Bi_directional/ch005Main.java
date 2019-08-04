package com.study.jong.persistence.ch005.Bi_directional;

import javax.persistence.EntityManager;
import java.util.List;

public class ch005Main {
    public static void main(String[] args) {
       EntityManager em = null;

       Team team = new Team("team1", "팀1");
       em.persist(team);

       Member member1 = new Member("member1", "회원1");
       member1.setTeam(team);
       team.getMembers().add(member1);
       em.persist(member1);

       Member member2 = new Member("member2", "회원2");
       member2.setTeam(team);
       em.persist(member2);

       // 양방향 연관관계는 연관관계의 주인이 외래 키를 관리한다. 따라서 주인이 아닌 방향은 값을 설정하지 않아도 데이터베이스에 외래 키 값이 정상 입력된다.
        team.getMembers().add(member1); // 무시

    }
}
