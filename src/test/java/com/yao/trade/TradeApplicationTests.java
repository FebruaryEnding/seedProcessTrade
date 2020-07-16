package com.yao.trade;

import com.yao.trade.dao.IUserDao;
import com.yao.trade.dao.dto.RoleRequestDTO;
import com.yao.trade.dao.dto.RoleResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TradeApplicationTests {

	@Autowired
	private IUserDao userDao;

	@Test
	void contextLoads() {
	}

	@Test
	void getRole(){
		RoleRequestDTO roleRequestDTO = new RoleRequestDTO();
		roleRequestDTO.setAccountNumber("智障的鹅");
		roleRequestDTO.setServerName("国服");
		List<RoleResponseDTO> roleResponseDTOS = userDao.queryRole(roleRequestDTO);
		RoleRequestDTO roleRequestDTO2 = new RoleRequestDTO();
		roleRequestDTO2.setAccountNumber("867160589");
		roleRequestDTO2.setServerName("国际服");
		List<RoleResponseDTO> roleResponseDTOS1 = userDao.queryRole(roleRequestDTO2);
		System.out.println("--");
	}

}
