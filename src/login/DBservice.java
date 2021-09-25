package login;

import common.MemberDTO;

public interface DBservice {
	public MemberDTO LoginCheck(String userId);

	public MemberDTO FindId(String name);
}
