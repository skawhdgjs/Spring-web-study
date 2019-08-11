package com.study.jong.persistence.ch008;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class ProxyExample {
}


@Setter
@Getter
class TargetClass {
    private Long id;
    private String name;
}


class ProxyClass extends TargetClass{

    TargetClass targetClass = null;

    // 호출시점에 객체를 만들어 값 반환
    @Override
    public Long getId(){
        if(targetClass == null ){
            targetClass = new TargetClass();
        }

        return targetClass.getId();
    }
}

class DumyMain {

    public void run(){
        ProxyClass proxy = new ProxyClass();

    }
}
