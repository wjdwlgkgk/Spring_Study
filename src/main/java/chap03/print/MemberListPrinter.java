package chap03.print;

import chap03.Data.Member;
import chap03.Data.MemberDao;

import java.util.Collection;

public class MemberListPrinter {
    private MemberDao memberDao;
    private MemberPrinter printer;

    public MemberListPrinter(MemberDao memberDao, MemberPrinter printer){
        this.memberDao = memberDao;
        this.printer = printer;
    }

    public void printAll(){
        Collection<Member> members = memberDao.selectAll();
        for(Member member : members){
            printer.print(member);
        }
    }

}
