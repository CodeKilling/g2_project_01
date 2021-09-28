package stats;

import java.util.ArrayList;

import common.snrDTO;

public interface StatsDBService {
	public ArrayList<snrDTO> search(String sd, String ed);
	public ArrayList<String> getCombo();
}
