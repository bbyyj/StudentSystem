package com.example.backend.service.impl;

import com.example.backend.repository.AdminRepository;
import com.example.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    @Override
    public UserDetailsService adminDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String netId) {
                return adminRepository.findByNetId(netId)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
