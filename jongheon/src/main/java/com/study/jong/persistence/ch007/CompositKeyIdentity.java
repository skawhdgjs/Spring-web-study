package com.study.jong.persistence.ch007;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

public class CompositKeyIdentity {
}

@Setter
@Getter
@Entity
class ParentIden {
    @Id @Column(name = "PARENT_ID")
    private String id;
    private String name;
}

@Setter
@Getter
@Entity
@IdClass(ChildIdIden.class)
class childIden {

    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent parent;

    @Id
    @Column(name = "CHILD_ID")
    private String childId;

    private String name;
}

@Setter
@Getter
@EqualsAndHashCode
class ChildIdIden implements Serializable {
    private String parent;
    private String childId;
}

@Setter
@Getter
@Entity
@IdClass(grandChildIdiden.class)
class grandChildiden {
    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private childIden idIden;

    @Id @Column(name = "GRANDCHILD_ID")
    private String id;
}

@Setter
@Getter
@EqualsAndHashCode
class grandChildIdiden implements Serializable {

    private ChildIdIden child;
    private String id;

}

/***
 *  EmbeddedId // @MapsId를 사용해야 한다.
 */

@Setter
@Getter
@Entity
class ParentEmbed2 {

    @Id
    @Column(name = "PARENT_ID")
    private String id;

    private String name;

}

@Setter
@Getter
@Entity
class ChildEmbed {

    @EmbeddedId
    private ChildIdEmbed id;

    @MapsId("parentId") // ChildIdEmbed.parentId를 매핑
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent parent;

    private String name;

}

@Setter
@Getter
@Embeddable
@EqualsAndHashCode
class ChildIdEmbed implements Serializable {

    public String parentId;

    @Column(name = "CHILD_ID")
    private String id;
}

@Setter
@Getter
@Entity
class GrandChildEmbed {

    @EmbeddedId
    private GrandChildIdEmbemd id;

    @MapsId("childId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private ChildEmbed child;

}

@Embeddable
@EqualsAndHashCode
class GrandChildIdEmbemd implements Serializable {

    private String childId;

    @Column(name = "GRANDCHILD_ID")
    private String id;
}