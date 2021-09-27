package dbservice;

import dto.MembershipDTO;

public interface DBService {
	public int checkInfo(String userId);
	public int insertMember(MembershipDTO dto);

}
