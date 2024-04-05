package com.example.backend.service.impl;

import com.example.backend.entities.Admin;
import com.example.backend.entities.Student;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String netId) {
                // 首先尝试从 adminRepository 中查找用户
                Optional<Admin> adminOptional = adminRepository.findByNetId(netId);

                // 如果在 adminRepository 中找到用户，则返回该用户
                if (adminOptional.isPresent()) {
                    Admin admin = adminOptional.get();
                    return new User(admin.getNetId(), admin.getPassword(), admin.getAuthorities());
                }

                // 如果在 adminRepository 中找不到用户，则尝试从 studentRepository 中查找用户
                Optional<Student> studentOptional = studentRepository.findByNetId(netId);

                // 如果在 studentRepository 中找到用户，则返回该用户
                if (studentOptional.isPresent()) {
                    Student student = studentOptional.get();
                    return new User(student.getNetId(), student.getPassword(), student.getAuthorities());
                }
                // 如果在 adminRepository 和 studentRepository 中都找不到用户，则抛出 UsernameNotFoundException
                throw new UsernameNotFoundException("User not found with netId: " + netId);
            }

        };
    }

}
