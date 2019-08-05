package com.study.jong.persistence.ch007;

import javax.persistence.*;

public class MappedSuperC {
}

@MappedSuperclass
abstract class BaseEntity {

    @Id @GeneratedValue
    private Long id;
    private String name;
}

@Entity
class MemberBase extends BaseEntity {

    // ID, NAME 상속
    private String email;
}

@Entity
@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID"))
// ㄴ 매핑 정보 재정의
class Seller extends BaseEntity{

}