package chap03.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberDao {

    private static long nextId = 0;
    private Map<String, Member> map = new HashMap<>();

    public MemberDao(){
        System.out.println("\n\n MemberDao ����\n\n");
    }

    public Collection<Member> selectAll(){
        return map.values();
    }


    public Member selectByEmail(String email){
        return map.get(email);
    }

    public void insert(Member member){
        member.setId(++nextId);
        map.put(member.getEmail(), member);
    }

    public void update(Member member){
        map.put(member.getEmail(), member);
    }
}
