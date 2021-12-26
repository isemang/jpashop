package com.hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")   //나는 뭐랑 연결되어있는지(반대편 사이트가 뭔지) 알려주는거
    //팀으로 매핑이 되어있다는 뜻!
    //mappedBy가 적혀져 있는 애는 "주인이 아니다" / 조회만 가능하다
    //이 연관관계의 주인은 Member객체 안에 설정되어있는 Team이다!
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }
}
/*
mappedBy 완전 중요.!!
- 어느 타이밍에 써야하는지 감이 안잡힐 수 있다,,
[객체와 테이블 간 연관관계를 맺는 차이]
- 객체 연관관계 = 2개
1) 회원 -> 팀 연관관계 1개(단방향)
2) 팀 -> 회원 연관관계 1개(단방향)
--> 사실상 단방향 연관관계가 두 개 있는 것(억지로 양방향으로 맺어준 것)

- 그와 달리 테이블 연관관계 = 1개(외래키로 이루어짐)
- 회원 <-> 팀 간 양방향 연관관계

[이러한 차이로 인해 나타나는 딜레마]
- 현재 member - team 간 관계를 위해 방향 두 개 양쪽으로 설정해 둠
- 멤버를 바꾸고 싶거나, 팀을 변경하고 싶을 경우
--> Member에 있는 Team값을 바꿔야 할지, Team에 있는 Member 값을 바꿔야 할 지 딜레마 발생
- 외래키를 어떻게 업데이트 해야할 지에 대한 고민 발생!!

--> 둘 중 하나로 외래키를 관리해야 한다(외래키. 연관관계의 주인을 정해야 한다)

[연관관계의 주인]
- 양방향 매핑으로 인해 발생하는 개념
- 객체의 두 관계 중 하나를 연관관계의 주인으로 지정
- 연관관계의 주인만이 외래 키를 관리(등록. 수정)
- "주인이 아닌 쪽은 읽기만 가능!!!!"
- 주인은 mappedBy 속성을 사용하지 않음
- "주인이 아니라면", mappedBy 속성 사용

[그럼, 누구를 주인으로 할 것인가]
- "외래키가 있는 곳을 주인으로 정해라!!"
- 일대다 관계에서, "1"이 있는 쪽에다가 mappedBy속성을 걸어라
- member-team관계에서, team 쪽이 "1" 임 -> member.team이 연관관계의 주인이 됨
 */