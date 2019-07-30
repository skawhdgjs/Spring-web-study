package com.study.jong.persistence.ch004.entity;

import com.study.jong.persistence.ch004.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

// 테이블과 매핑할 클래스 , JPA가 관리하게 됨 중복안된게 주의 * 주의 사항: 기본생성자 필요, final(field도), enum, interface, inner 클래스에는 사용할 수없다.
@Entity(name = "Member2")
@NoArgsConstructor
@AllArgsConstructor
// 앤티티와 매핑할 테이블을 지정 ( 생략하면 엔티티 이름을 테이블 이름으로 사용)
@Table(name = "Member2", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)}) // DDL기능들은 자동생성할때만 사용되고 실행로직에는 영향을 주지 않는다.
public class MemberEntity {

    @Id
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 길이 제한이 없는 필드 , CLOB, BLOB 타입을 매핑
    @Lob
    private String description;
}
