package com.study.jong.persistence.ch004.entity;

import javax.persistence.*;

@Entity
@TableGenerator(
        name = "BOARD_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "BOARD_SEQ", allocationSize = 1
)
// pkColumnValue : 시퀀스 컬럼명
// valueColumnName : 시퀀스 값 칼럼명
// pkColumnValue, 키로 사용할 값 이름
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BOARD_SEQ_GENERATOR")
    private Long id;

}
