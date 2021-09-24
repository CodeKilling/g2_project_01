package login;

import common.MemberDTO;

public interface DBservice {
	public MemberDTO LoginCheck(String userId);

}
