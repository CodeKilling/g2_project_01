package KHS.dbService;

import KHS.dto.bookDTO;

public interface dbService {
	// 입고, 출고
		public int warehousing(bookDTO dto);
		public int release(bookDTO dto);
}
