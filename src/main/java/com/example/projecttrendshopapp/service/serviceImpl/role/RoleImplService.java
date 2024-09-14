package com.example.projecttrendshopapp.service.serviceImpl.role;

import com.example.projecttrendshopapp.dao.entity.RoleEntity;
import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import com.example.projecttrendshopapp.dao.repository.RoleRepository;
import com.example.projecttrendshopapp.dao.repository.UsersRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleImplService implements RoleService{
    private final RoleRepository roleRepository;
    private final UsersRepository usersRepository;

    @Override
    public void add(String username) {
        log.info("ActionLog.add.started:username {}", username);
        RoleEntity roleEntity = roleRepository.findRoleEntityByUser_Username(username).orElseThrow(() -> new NotFoundException("Username not found"));
        RoleEntity role = new RoleEntity(null, "ROLE_ADMIN", roleEntity.getUser());
        roleEntity.getUser().getRoles().add(role);
        roleRepository.save(role);
        log.info("ActionLog.add.end:username {}", username);
    }

    @Override
    @Transactional
    public void remove(Long userId, Long roleId) {
        log.info("ActionLog.remove.started: userId {},roleId {}", userId, roleId);
        UsersEntity user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("roleId not found"));
        roleRepository.deleteById(role.getId());
        user.getRoles().removeIf(it -> it.getName().equals(role.getName()));
        usersRepository.save(user);
        log.info("ActionLog.remove.end: userId {},roleId {}", userId, roleId);
    }
//    UsersEntity user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
//        roleRepository.deleteById(roleId);
//    List<RoleEntity> roles = user.getRoles();
//        roles.removeIf(it -> it.getId().equals(roleId));
//        user.setRoles(roles);
//        usersRepository.save(user);

}
