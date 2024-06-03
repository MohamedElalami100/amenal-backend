package com.amenal.amenalbackend.security.admin;

import com.amenal.amenalbackend.security.user.Role;
import com.amenal.amenalbackend.security.user.UserDto;
import com.amenal.amenalbackend.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String gett() {
        return "ok";
    }
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("users/manager/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    //@Hidden
    public ResponseEntity<?> putMn(@PathVariable("id") Integer id) {
        userService.changeStatus(id, Role.MANAGER);
        return ResponseEntity.ok().build();
    }

    @PutMapping("users/supManager/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> putSup(@PathVariable("id") Integer id) {
        userService.changeStatus(id, Role.SUPMANAGER);
        return ResponseEntity.ok().build();
    }

    @PutMapping("users/admin/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> putAdm(@PathVariable("id") Integer id) {
        userService.changeStatus(id, Role.ADMIN);
        return ResponseEntity.ok().build();
    }

    @PutMapping("users/user/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> putUser(@PathVariable("id") Integer id) {
        userService.changeStatus(id, Role.USER);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("users/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
