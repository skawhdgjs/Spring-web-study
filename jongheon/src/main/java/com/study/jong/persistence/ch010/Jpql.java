package com.study.jong.persistence.ch010;

import javax.persistence.*;
import java.util.List;

public class Jpql {

    public void jqplQuery(){
        EntityManager em = null;

        String jpql = "select m from JpqlMember as m where m.username ='kim'";
        List<JpqlMember> resultList = em.createQuery(jpql).getResultList();
    }

}

@Entity(name = "MemberJpa")
class JpqlMember{

    @Id @GeneratedValue
    private Long id;

    @Column
    private String username;

}

