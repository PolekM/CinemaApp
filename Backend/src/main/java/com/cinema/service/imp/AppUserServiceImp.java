package com.cinema.service.imp;

import com.cinema.dto.AppUser.UserChangeRoleDto;
import com.cinema.dto.AppUser.UserListDto;
import com.cinema.dto.AppUser.UserReadDataDto;
import com.cinema.dto.auth.ChangePasswordDto;
import com.cinema.entity.AppRole;
import com.cinema.entity.AppUser;
import com.cinema.exception.auth.WrongPasswordException;
import com.cinema.repository.AppUserRepository;
import com.cinema.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImp implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImp(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> changeUserPassword(ChangePasswordDto changePasswordDto) {
        AppUser user = appUserRepository.findByLogin(getCurrentUser());

        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            throw new WrongPasswordException("Old Password isn`t correct");
        }
        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getRepeatNewPassword())) {
            throw new WrongPasswordException("Password aren`t the same");
        }
        user.updateUserPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        appUserRepository.save(user);
        return new ResponseEntity<>("Your Password has been change", HttpStatus.OK);
    }

    @Override
    public UserReadDataDto getUserData() {
        AppUser user = appUserRepository.findByLogin(getCurrentUser());
        UserReadDataDto userReadDataDto = new UserReadDataDto(user);
        return userReadDataDto;
    }



    @Override
    public List<UserListDto> getAllUser(Integer pageNo, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize);
        Page<AppUser> allUsers = appUserRepository.findAll(pageRequest);
        return allUsers.getContent().stream().map(UserListDto::new).collect(Collectors.toList());
    }

    @Override
    public List<AppRole> getAllUserRole() {
        return appUserRepository.findAllRole();
    }

    @Override
    public ResponseEntity<String> changeUserRole(UserChangeRoleDto appRole) {
        AppUser appUser = appUserRepository.findById(appRole.getId()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        appUser.updateUserRole(appRole.getAppRole());
        appUserRepository.save(appUser);
        return new ResponseEntity<>("Your role has been change", HttpStatus.OK);
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();

        } else {
            throw new UsernameNotFoundException("User Not Found");
        }

    }
}
