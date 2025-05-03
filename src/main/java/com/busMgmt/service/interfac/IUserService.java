package com.busMgmt.service.interfac;

import com.busMgmt.dto.LoginRequest;
import com.busMgmt.dto.Response;
import com.busMgmt.entity.User;

public interface IUserService {

    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response deleteUser(Long userId);

    Response getUserById(Long userId);

    Response getMyInfo(String email);

}
