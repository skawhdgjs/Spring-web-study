package com.study.jong.persistence.ch002;

import com.study.jong.persistence.ch002.entity.MemberEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Component
public class JpaMain {

    @Autowired
    LocalContainerEntityManagerFactoryBean factoryBean;

    public void run() {
        System.out.println(factoryBean.getPersistenceUnitName());

        // 엔터티 매니저는 JPA설정 정보를 읽어서 JPA구현체에 따라서 커낸션 풀도 생성
        // 생성비용이 크므로 딱한번만 생성해서 공ㅇ
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        // 대부분의 기능은 엔터티 매니저가 생성
        // 엔터티 매니저를 동해 데이터 베이스 CRUD 가능 , db와 connection유지 ( 스레드간 공유 금지 및 재사용 금지 )
        // 엔터티 매니저를 가상의 데이터베이스와 동일하게 생각 (
        EntityManager em = emf.createEntityManager();
        em.setFlushMode(FlushModeType.AUTO);
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch ( Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    // member 엔터티 <> Table이고 entityManager가 가상 DB
    private void logic(EntityManager em) {
        String id = "id1";
        MemberEntity member = new MemberEntity();
        member.setId(id);
        member.setUsername("jong");
        member.setAge(2);

        // 등록
        em.persist(member);

        // 수정
        member.setAge(20);

        MemberEntity findMember = em.find(MemberEntity.class, id);

        // 이 sql은 jpql -> 엔터티 객체를 중심으로 개발하기 때문에 검색도 테이블이 아닌 엔터티 객체를 대상으로 검색
        // JPQL = 객체지향 쿼리 언어 SQL과 차이점은 앤터티 객체를 대상으로 쿼리(= 클래스와 필드를 대상으로 쿼리)
        List<MemberEntity> memberEntities = em.createQuery("select m from MemberEntity m", MemberEntity.class)
                                                .getResultList();

        em.createQuery("select m.id, m.username, m.age from MemberEntity m");
        em.remove(member);

    }
}
