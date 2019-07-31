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
//@SequenceGenerator(
//        name = "BOARD_SEQ_GENERATOR",
//        sequenceName = "BOARD_SQL",
//        initialValue = 1, allocationSize = 1
//                          ㄴ 시퀀스 한번 호출에 증가하는수 (성능 최적화에 사용 )
//)
@Access(AccessType.FIELD)
// ㄴ JPA 접근 방식 설정 private 이여도 필드에 직접 접근 가능
//@Access(AccessType.PROPERTY)
// ㄴ getter을 통해 접근 .
// Default로는 id를 기준으로 접근 방식이 결정됨 ex. @ID필드가 있으니 FIELD타입으로 설정됨
/**
 * {@link MemberEntity#getId()}
 */
public class MemberEntity {

    // 자바 기본형, 자바 wrapper형(String, date, bigDecimal, bigInteger )
    // 기본 키를 애플리케이션에서 직접 할당하는게 아니라 데이터베이스 생성해주는 값을 사용( ex AUTO_INCREMENTAL)
    // @GeneratedValue(GenerationType.IDENTITY) -> MYSQL, PostGreSql, Sql server,DB2 [ 기본 키값을 DB에 저장후 다시 조회해야하기 때문에 데이터베이스를 추가로 조회한다 ]
    // ㄴ 이전략 사용시 쓰기지연 전략은 동작하지 않는다.
    // @GeneratedValue(value = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR") -> Oracle, PostGresql, DB2, H2
    // ㄴ 데이터 베이스 시퀀스를 사용해서 식별자 조회후 엔터티에 할당후 엔티티를 영속성 컨텍스트에 저장 이후 트랜잭션 커밋, 플러쉬
    // @GeneratedValue(GenerationType.TABLE) -> 키 전략 생성 ( 모든 데이터베이스에 생성 가능 )
    // ㄴ 키 생성 전용 테이블을 하나 만듬
    /**
     * {@link BoardEntity}
     */
    @Id
    @GeneratedValue(GenerationType.AUTO)
    private String id;

    @Id
    public String getId(){
        return id;
    }

    // 컬럼매핑
    @Column(name = "NAME", nullable = false, length = 10, unique = false, columnDefinition = "varchar(100) default 'EMPTY'")
    // DDL 생성 기능 사용시 java 기본 타임은 not null 을 추가, 반면에 객체 타입은 null 가능 ( 기본 기능 or Column 어노테이션 없을떄)
    // 자바 기본 타입시 nullable = false 을 원한다면 지정하자!
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    // ㄴ enum 순서가 바뀌어도 안전하나 저장되는 크기가 큼
    // @Enumerated(EnumType.ORDINAL)
    // ㄴ 데이베이스에 저장되는 크기가 작고 이넘 순서대로 저장
    private RoleType roleType;

    // util.Data, util.Calendar 매핑할때 사용 ( mysql의 경우 datatime으로 됨)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 길이 제한이 없는 필드 , CLOB, BLOB 타입을 매핑
    //CLOB : 문자일 경우 , 나머지 BLOB
    @Lob
    private String description;

    @Transient
    // 무시
    private String musi;

    @Access(AccessType.PROPERTY)
    public String getFullName () {
        return musi;
    }
}
