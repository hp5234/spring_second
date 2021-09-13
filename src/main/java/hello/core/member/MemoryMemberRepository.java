package hello.core.member;

import ch.qos.logback.core.OutputStreamAppender;
import com.sun.source.tree.MemberReferenceTree;

import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;

// 데이터베이스 방식이 미확정으로
// 메모리에 저장 하는 방식
// 메모리에서만 진행하므로 테스트용으로만 사용해야 함
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 이슈가 발생 할 수 있으므로 동시성 제어를 해야하나
    // 해당 예제에선 사용되지 않음 - 일반 HashMap으로 작성

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
