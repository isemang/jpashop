package com.hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Member {

    @Id
    private Long id;    //PK

    @Column(name = "name", nullable = false)
    private String username;    //컬럼명은 name, 객체에선 username으로 사용

    private int age;

    @Enumerated(EnumType.STRING)    //기본이 ORDINAL, ORDINAL은 쓰지 말 것
    private RoleType roleType;  //객체에서 Enumtype을 사용하고 싶으면 -> Enumerated annotation 사용

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;   //java의 dateclass : 날짜랑 시간 다 있는데, DB에는 시간, 날짜, 날짜시간 다 구분되어있음

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;     //큰 형식(clob)

    @Transient
    private int temp;   //DB랑 관계없이 만든 변수이면 Transient 어노테이션 이용 -> DB매핑 안해줌
    
    public Member() {
    }
}
