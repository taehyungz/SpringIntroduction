package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 동시성 문제때문에 ConcurentHashMap을 사용한다
    private static long sequence = 0L; //실무에서는 동시성 문제때문에 AtomicLong을 사용한다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() { //자바에서는 실무할 때 List를 많이 쓴다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
