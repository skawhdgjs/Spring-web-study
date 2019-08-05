package com.study.jong.persistence.ch007;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

public class CompositKey {
}

@Entity
@IdClass(value = ParentId.class)
class Parent {
    @Id
    @Column(name = "PARENT_ID1")
    private Long id1;

    @Id
    @Column(name = "PARENT_ID2")
    private Long id2;

    private String name;
}

@Entity
class child {

    @Id
    @Column(name = "CHILD_ID")
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID1", referencedColumnName = "PARENT_ID1"),
            @JoinColumn(name = "PARENT_ID2", referencedColumnName = "PARENT_ID2")
    })
    // name 과 referencedColumnName 같으면 생략해도됨!
    private Parent parent;
}

// 식별자 클래스의 속석명과 엔티티에서 사용하는 식별자의 속석명이 같아야함
// serialize 인터페이스 구현
// 기본색성자, equls, hascode있어야함
// public 이여야함
@NoArgsConstructor
@EqualsAndHashCode
class ParentId implements Serializable {

    private String id1;
    private String id2;

    public ParentId(String id1, String id2){
        this.id1 = id1;
        this.id2 = id2;
    }

}



@Entity
class ParentEmbed {

    @EmbeddedId
    private ParentIdEmbed id;
}


// @Embeddalbe 어노테이션
// serialize 인터페이스 구현
// 기본색성자, equls, hascode있어야함
// public 이여야함
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
class ParentIdEmbed implements Serializable{
    private String id1;
    private String id2;
}