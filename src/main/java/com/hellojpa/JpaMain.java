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
        try {
            List<Member> result = em.createQuery("select m from Member as m")
                    .setFirstResult(1)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        enf.close();
    }
}
