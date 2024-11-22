package com.sp_databazy.Service;

import com.sp_databazy.Entity.Pouzivatel;
import com.sp_databazy.Repository.PouzivatelRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PouzivatelService pouzivatelService;

    public MyUserDetailsService(PouzivatelService pouzivatelService) {
        this.pouzivatelService = pouzivatelService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pouzivatel pouzivatel = pouzivatelService.getPouzivatelByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(pouzivatel.getEmail())
                .password(pouzivatel.getHeslo()) // Heslo musí byť šifrované
                .roles(pouzivatel.getRola().toString()) // Konvertujeme rolu na String
                .build();
    }
}
