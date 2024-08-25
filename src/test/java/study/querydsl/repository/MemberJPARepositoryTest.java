package study.querydsl.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJPARepositoryTest {

    @Autowired
    EntityManager em;
    
    @Autowired
    MemberJPARepository memberJPARepository;
    
    @Test
    public void basicTest() throws Exception {
        Member member = new Member("member1", 10);
        memberJPARepository.save(member);

        Member findMember = memberJPARepository.findById(member.getId()).get();
        assertThat(findMember.getId()).isEqualTo(member.getId());

        List<Member> result1 = memberJPARepository.findAll_Querydsl();
        assertThat(result1).containsExactly(member);

        List<Member> result2 = memberJPARepository.findByUsername_Querydsl("member1");
        assertThat(result2).containsExactly(member);
    }
}