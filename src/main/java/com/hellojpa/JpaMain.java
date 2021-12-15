package com.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //db 당 하나만 생성
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");
        //고객의 요청이 올 때마다 계속 썼다가, 버렸다가 함
        //그래서 엔티티매니저는 쓰레드간에 절대 절대 공유하면 안됨!!!
        //JPA의 모드 데이터 변경은 트랜잭션 안에서 실행해야 함
        EntityManager em = enf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        /* 1. member 추가 */
//        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");
//
//            em.persist(member);
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        /* 2. member 수정 */
//        try {
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        /* 3. member 삭제 */
//        try {
//            Member findMember = em.find(Member.class, 1L);
//
//            em.remove(findMember);
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        /* 4. JPQL 이용 */
        //JPQL : 쿼리를 짜는 거긴 하지만, SQL처럼 RDB 종속적인 쿼리가 아니라 "객체"에 종속적인 쿼리를 짤 수 있음
        //또한 ORACLE에서 My Sql로 바꿔도 전~혀 코드상에서 바꿀 필요가 없음
//        try {
//            List<Member> result = em.createQuery("select m from Member as m")
//                    .setFirstResult(1)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        /* 5. 쓰기지연 SQL 저장소 */
//        try {
//
//            //영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("==================");
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        /* 6. 엔티티 수정, 변경 감지 */
//        try {
//
//            //영속
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZ");
//
//            //em.persist 안해도 됨
////            em.persist(member);
//
//            System.out.println("==================");
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        /* 7.플러시 */
//        try {
//
//            //영속
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            //플러시 강제 호출
//            em.flush();
//
//            System.out.println("==================");
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        /* 준영속 : 영속성 컨텍스트가 제공하는 기능 사용 불가 */
//        try {
//
//            //영속상태
//            Member member = em.find(Member.class, 150L);
//            member.setName("AAAAA");
//
//            //준영속 상태로 변경, JPA에서 관리 안하도록 만듬
////            em.detach(member);
//            em.clear(); //엔티티 매니저 속 영속성 컨텍스트가 통째로 다 지워짐
//
//            //영속성 컨텍스트 clear로 인해 1차캐시에 없음, 같은맴버 재조회 시 셀렉트 쿼리 또 나감
//            Member member2 = em.find(Member.class, 150L);
//
//            System.out.println("==================");
//
//            //JPA에서 관리 안하도록 만들었으니 데이터를 변경했음에도 업데이트 안됨
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        try {
            Member member = new Member();

            member.setUsername("C");

            System.out.println("============");
            em.persist(member);
            System.out.println("member.getId() = " + member.getId());
            System.out.println("============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        enf.close();
    }
}
