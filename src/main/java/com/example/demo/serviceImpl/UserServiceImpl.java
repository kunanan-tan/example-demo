package com.example.demo.serviceImpl;

import com.example.demo.bean.ResponseModelDto;
import com.example.demo.bean.UserBean;
import com.example.demo.bean.UserResponseBean;
import com.example.demo.config.TokenConfig;
import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl {

    private final TokenConfig tokenConfig;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseModelDto<UserResponseBean> login(UserBean request){
        ResponseModelDto<UserResponseBean> responseModelDto;

        try {
            if (request == null) {
                return responseModelDto = new ResponseModelDto<>(null, false, "error", "data fail");
            }

            Users users = usersRepository.findByEmail(request.getEmail().trim());
            if(users == null){
                return responseModelDto = new ResponseModelDto<>(null, false, "error", "login fail");
            }

            if (!matchPassword(request.getPassword(), users.getPassword())) {
                return responseModelDto = new ResponseModelDto<>(null, false, "error", "login fail");
            }

            UserResponseBean response = new UserResponseBean();
            response.setToken(tokenConfig.generateToken(request));

            return responseModelDto = new ResponseModelDto<>(response, true);

        }catch (Exception e){
            return responseModelDto = new ResponseModelDto<>(null, false, "error", "service error");

        }
    }

    public Users create(UserBean request)  {

        Users entity = new Users();
        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));

        return usersRepository.save(entity);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
