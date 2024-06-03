package com.amenal.amenalbackend.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = repository.findAll();
        for (User user: users) {
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();
            userDtos.add(userDto);
        }
        return  userDtos;
    }

    public User changeStatus(String email, Role role) {
        User user = repository.findByEmail(email).get();
        user.setRole(role);
        return repository.save(user);
    }

    public void delete(String email) {
        User user = repository.findByEmail(email).get();
        repository.delete(user);
    }

    public void changeStatus(Integer id, Role role) {
        User user = repository.findById(id).get();
        user.setRole(role);
        repository.save(user);
    }

    public void delete(Integer id) {
        User user = repository.findById(id).get();
        repository.delete(user);
    }
}
