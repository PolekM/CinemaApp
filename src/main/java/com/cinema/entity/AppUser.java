package com.cinema.entity;

import com.cinema.dto.auth.AuthSaveDto;
import com.cinema.dto.auth.ChangePasswordDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "app_user")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer UserId;
    private String login;
    private String password;
    private String email;
    @ManyToOne
    @JoinColumn(name = "user_role")
    private AppRole userRole;

    public AppUser(AuthSaveDto authSaveDto){
        this.login =authSaveDto.getLogin().toLowerCase();
        this.password = authSaveDto.getPassword();
        this.email = authSaveDto.getEmail();
        this.userRole = new AppRole(2,"ROLE_USER");
    }

    public AppUser updateUserPassword(String updatedPassword){
        this.password = updatedPassword;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.userRole.getRoleName()));
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
