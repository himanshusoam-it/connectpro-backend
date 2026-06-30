package com.himanshu.connectpro.service;

import com.himanshu.connectpro.dto.ApiResponse;
import com.himanshu.connectpro.dto.RegisterRequest;
import com.himanshu.connectpro.dto.RegisterResponse;

public interface UserService {

    ApiResponse<RegisterResponse> register(RegisterRequest request);

}
