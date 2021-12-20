package com.hellojpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TEAM_ID")   //참조 대신 외래키를 그대로 사용
    private Long teamId;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    //하나의 팀에 여러 사람이 소속됨 -> manyToOne(member입장에선 many, team입장에서는 one)
    private Team team;  //Team과 Member의 관계가 일대다, 다대일 관계인지 알려줘야 함

    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    //연간관계 편의 메서드 사용!!
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
