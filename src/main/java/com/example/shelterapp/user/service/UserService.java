package com.example.shelterapp.user.service;

import com.example.shelterapp.user.repository.RoleRepository;
import com.example.shelterapp.user.repository.UserRepository;
import com.example.shelterapp.user.mapper.UserMapper;
import com.example.shelterapp.user.model.Role;
import com.example.shelterapp.user.model.User;
import com.example.shelterapp.user.model.dto.UserDTO;
import com.example.shelterapp.user.model.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.shelterapp.user.model.ERole.USER;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public List<UserListDTO> allUsersForList() {
        List<User> allUsers = userRepository.findAll();
        User admin = userRepository.findByUsername("admin")
                .orElseThrow(() -> new EntityNotFoundException("There is no admin user at the moment!"));
        allUsers.remove(admin);
        return allUsers
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Email
    public UserDTO create(UserDTO user) {
        Role defaultRole = roleRepository.findByName(USER)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find USER role"));
        User user1 = userMapper.fromDTO(user);
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        user1.setRoles(roles);
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userMapper.toDTO(userRepository.save(user1));
    }

    @Email
    public UserDTO edit(Long id, UserDTO user) {
        User actUser = findById(id);
        actUser.setEmail(user.getEmail());
        actUser.setUsername(user.getUsername());
        actUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toDTO(
                userRepository.save(actUser)
        );

    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() { userRepository.deleteAll(); }

    public UserDTO get(Long id) {
        return userMapper.toDTO(findById(id));
    }

}
