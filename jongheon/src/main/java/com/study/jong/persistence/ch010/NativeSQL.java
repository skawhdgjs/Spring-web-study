package com.study.jong.persistence.ch010;

import javax.persistence.EntityManager;
import java.util.List;

public class NativeSQL {
    public void tmp(){
        String sql = "SELECT ID, AGE, TEAM, NAME FROM MEMBER";

        EntityManager em = null;

        List<MemberNative> result = em.createNativeQuery(sql, MemberNative.class).getResultList();
    }
}

class MemberNative {
}
