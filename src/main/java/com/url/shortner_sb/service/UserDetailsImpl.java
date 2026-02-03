package com.url.shortner_sb.service;

import com.url.shortner_sb.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID=1L;
    private Long id;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, Collection<? extends GrantedAuthority> authorities, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(User user){
        GrantedAuthority authority=new SimpleGrantedAuthority(user.getRole());
        return new UserDetailsImpl(
                user.getId(),
                Collections.singletonList(authority), // ✅ correct position
                user.getEmail(),
                user.getUsername(),


                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUsername(){
        return username;
    }
}
