package dbservice;

import common.MemberDTO;

public interface DBService {
	public int checkInfo(String userId);
	public int insertMember(MemberDTO dto);

}
