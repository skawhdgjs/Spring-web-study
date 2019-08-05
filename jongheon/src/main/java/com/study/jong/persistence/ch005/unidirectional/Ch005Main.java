package com.study.jong.persistence.ch005.unidirectional;

import com.study.jong.persistence.ch005.unidirectional.Member;
import com.study.jong.persistence.ch005.unidirectional.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Ch005Main {
    public static void main(String[] args) {
        EntityManager em = null;

        Team team1 = new Team("Team1", "nam");
        em.persist(team1);

        Member member1 = new Member("Member1", "jong");
        Member member2 = new Member("Member2", "heon");


        member1.setTeam(team1);
        member2.setTeam(team1);

        em.persist(member1);
        em.persist(member2);

        // 탐색 1. 객체 그래프 탐색
        Member member = em.find(Member.class, "Member1");
        Team team = member.getTeam();
        System.out.println(team);

        // 탐색 2. 객체지향 쿼리 사용
        List<Member> resultList = em.createQuery("Select m from Member m join m.team t where t.name=:teamName").getResultList();

        // 연관관계 수정
        Team team2 = new Team("team2", "nam2");
        em.persist(team);

        Member memberForTeamChange = em.find(Member.class, "jong");
        memberForTeamChange.setTeam(team2);

        // 연관관계 제거
        member1.setTeam(null);

        // 연관된 엔터티를 삭제하려면 기존에 있던 연관관계를 모두 제거하고 삭제해야함 그렇제 않으면 에러가 발생한다
        member1.setTeam(null);
        member2.setTeam(null);
        em.remove(team);
    }
}
