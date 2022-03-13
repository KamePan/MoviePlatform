package cn.edu.ecnu.service;

import cn.edu.ecnu.model.dataobject.UserDO;
import cn.edu.ecnu.model.entity.JwtUser;
import cn.edu.ecnu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<UserDO> userDOS = userRepository.selectUserByUsername(s);
        UserDO userDO = null;
        if (!userDOS.isEmpty()) {
            userDO = userDOS.get(0);
        }
        return new JwtUser(userDO);
    }
}
