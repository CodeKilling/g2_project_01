package login;

import common.MemberDTO;

public interface DBservice {
	public MemberDTO LoginCheck(String userId);

	public MemberDTO FindId(String name);

	public MemberDTO rePwd(String userId, String pwd);

	public boolean CheckID(String userId);
}
