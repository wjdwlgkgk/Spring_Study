package chap03.RegisterService;

import chap03.Data.Member;
import chap03.Data.MemberDao;
import chap03.RegisterService.AlreadyExistingMemberException;
import chap03.RegisterService.RegisterRequest;

import java.util.Date;

public class MemberRegisterService {
    private MemberDao memberDao;

    public MemberRegisterService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    public void regist(RegisterRequest req){
        Member member = memberDao.selectByEmail(req.getEmail());
        if(member != null){
            throw new AlreadyExistingMemberException("dup email " + req.getEmail());
        }
        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), new Date());
        memberDao.insert(newMember);
    }

}
