package com.yao.trade.dao;

import com.yao.trade.dao.dto.*;

import java.util.List;

public interface IUserDao {

    List<RoleResponseDTO> queryRole(RoleRequestDTO query);

    void register(UserRegisterRequestDTO registerRequestDTO);

    UserResponseDTO findOne(String id);

    UserResponseDTO login(LoginRequestDto loginRequestDto);

}
