package dbservice;

import dto.MemberDTO;

public interface DBService {
	public int checkInfo(String userId);
	public int insertMember(MemberDTO dto);

}
