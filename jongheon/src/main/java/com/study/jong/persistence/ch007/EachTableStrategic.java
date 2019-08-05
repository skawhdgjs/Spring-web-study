package com.study.jong.persistence.ch007;

import javax.persistence.*;

public class EachTableStrategic {
}

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class ItemEach {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
}

@Entity
class AlbumEach extends ItemEach{

}