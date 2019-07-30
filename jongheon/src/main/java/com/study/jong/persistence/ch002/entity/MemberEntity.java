package com.study.jong.persistence.ch002.entity;

import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@DynamicUpdate
@Entity
// Table annotation이 없으면 기본적으로 entity이름을 상요
@Table(name = "Member")
public class MemberEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    // 매핑 어노테이션이 없으면 필드명을 사용해서 컬럼명을 매핑
    private Integer age;
}
