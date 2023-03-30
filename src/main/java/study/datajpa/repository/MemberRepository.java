package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    @Query(name = "Member.findByUsername")  // 네임드 쿼리
    List<Member> findByUsername(@Param("username") String username);

    // 엔티티 타입 조회
    @Query("select m from Member m where m.username = :username and age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    // 단순 값 하나 조회
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // DTO 조회
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :name")
    List<Member> findByNames(@Param("name") Collection<String> name);

    List<Member> findListByUsername(String username);  // 컬렉션
    Member findMemberByUsername(String username); //단건
    Optional<Member> findOptionalByUsername(String username); //단건 Optional
}
