package com.example.backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService {
    UserDetailsService adminDetailsService();
}
