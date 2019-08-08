package com.study.jong.persistence.ch007;

import javax.persistence.*;


public class SingleTablePolicy {


}

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// ㄴ Default Single
@DiscriminatorColumn()
abstract class ItemSingle {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;
    private int price;
}

@Entity
@DiscriminatorValue("A")
class AlbumSingle extends ItemSingle {
    private String singer;
}





