package com.yao.trade.dao.dto;

import com.alibaba.fastjson.JSON;
import com.yao.trade.common.BeanCopyUtils;
import com.yao.trade.common.HttpUtils;
import com.yao.trade.dao.IUserDao;
import com.yao.trade.domain.UserEntity;
import com.yao.trade.service.IUserService;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserDaoImpl implements IUserDao {

    @Autowired
    private IUserService userService;

    @Override
    public List<RoleResponseDTO> queryRole(RoleRequestDTO query) {
        if (StringUtils.isEmpty(query.getServerName())) {
            throw new RuntimeException("系统出错");
        }
        if (query.getServerName().equals("国服")) {
            try {
                String host = "https://poe.game.qq.com/character-window/get-characters?accountName=" + query.getAccountNumber() + "&realm=pc";
                String path = "";
                String method = "POST";
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                Map<String, String> querys = new HashMap<String, String>();
                Map<String, String> bodys = new HashMap<String, String>();
                HttpResponse response = null;
                response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
                String resultJson = EntityUtils.toString(response.getEntity());
                List<RoleItem> items = JSON.parseArray(resultJson, RoleItem.class);
                List<RoleResponseDTO> list = BeanCopyUtils.copyList(items, RoleResponseDTO.class);
                return list;
            } catch (Exception e) {
                throw new RuntimeException("用户不存在");
            }
        } else if (query.getServerName().equals("国际服")) {
            try {
                String host = "https://www.pathofexile.com/character-window/get-characters?accountName=" + query.getAccountNumber() + "&realm=pc";
                String path = "";
                String method = "POST";
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                Map<String, String> querys = new HashMap<String, String>();
                Map<String, String> bodys = new HashMap<String, String>();
                HttpResponse response = null;
                response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
                String resultJson = EntityUtils.toString(response.getEntity());
                List<RoleItem> items = JSON.parseArray(resultJson, RoleItem.class);
                List list = BeanCopyUtils.copyList(items, RoleResponseDTO.class);
                return list;
            } catch (Exception e) {
                throw new RuntimeException("用户不存在");
            }
        }
        return null;
    }

    @Override
    public void register(UserRegisterRequestDTO registerRequestDTO) {
        UserEntity userEntity = BeanCopyUtils.copy(registerRequestDTO, UserEntity.class);
        userService.save(userEntity);
    }

    @Override
    public UserResponseDTO findOne(String id) {
        UserEntity userEntity = userService.findOne(id);
        return BeanCopyUtils.copy(userEntity, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO login(LoginRequestDto loginRequestDto) {
        UserEntity userEntity = userService.findByUserNameAndPassword(loginRequestDto.getUserName(), loginRequestDto.getPassword());
        if (userEntity == null) {
            throw new RuntimeException("登录失败，账号或者密码不存在");
        }
        return BeanCopyUtils.copy(userEntity, UserResponseDTO.class);
    }

    @Override
    public void changeRole(ChangeRoleRequestDTO changeRoleRequestDTO) {
        UserEntity userEntity = userService.findOne(changeRoleRequestDTO.getUserId());
        if(userEntity == null){
            throw new RuntimeException("用户不存在");
        }
        userEntity.setRoleLevel(changeRoleRequestDTO.getRoleLevel());
        userEntity.setRoleName(changeRoleRequestDTO.getRoleName());
        userEntity.setAccountNumber(changeRoleRequestDTO.getAccountNumber());
        userEntity.setServerName(changeRoleRequestDTO.getServerName());
        userService.save(userEntity);
    }


}
