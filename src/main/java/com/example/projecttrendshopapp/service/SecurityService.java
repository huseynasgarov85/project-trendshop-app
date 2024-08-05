package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.entity.RoleEntity;
import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import com.example.projecttrendshopapp.dao.repository.UsersRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        UsersEntity user = usersRepository.findByName(username).orElseThrow(()-> new NotFoundException("username not found"));
        return new User(user.getName(),user.getPassword(),getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UsersEntity usersEntity){
        List<RoleEntity> roles = usersEntity.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
