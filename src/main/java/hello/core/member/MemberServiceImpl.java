package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 인터페이스
    // 구현체
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 기존에 있던 구현체 부분을 지워주고 생성자를 통해 주입받는다.(생성자 주입 방식)
    // 이로인해 MemberServiceImpl 는 추상화에만 의존하고 구현체는 전혀 알지 못한다.
    private final MemberRepository memberRepository;

    // 생성자
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 스프링의 싱글톤보장을 테스트하기 위한 테스트용도
    // ConfigurationSingletonTest 에서 테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
