package chap03.print;

import chap03.Data.Member;

public class MemberPrinter {
    public void print(Member member){
        System.out.printf("ȸ������ : ���̵�=%d, �̸���=%s, �̸�=%s, �����=%tF\n",
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getRegisterDate());
    }
}
