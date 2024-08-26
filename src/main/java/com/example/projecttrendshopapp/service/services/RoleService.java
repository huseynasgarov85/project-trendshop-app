package com.example.projecttrendshopapp.service.services;

import com.example.projecttrendshopapp.dao.entity.RoleEntity;
import com.example.projecttrendshopapp.dao.repository.RoleRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void add(String username) {
        log.info("ActionLog.add.started:username {}", username);
        RoleEntity roleEntity = roleRepository.findRoleEntityByUser_Username(username).orElseThrow(() -> new NotFoundException("Username not found"));
        RoleEntity role = new RoleEntity(null, "ROLE_ADMIN", roleEntity.getUser());
        roleEntity.getUser().getRoles().add(role);
        roleRepository.save(role);
        log.info("ActionLog.add.end:username {}", username);
    }

}
