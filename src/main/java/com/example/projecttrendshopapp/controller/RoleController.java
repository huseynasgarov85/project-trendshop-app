package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.service.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public void add(@RequestParam String username) {
        roleService.add(username);
    }

    @DeleteMapping
    public void remove(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        roleService.remove(userId, roleId);
    }

}
