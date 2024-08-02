package com.example.bootagit_project01.user.user.service;

import com.example.bootagit_project01.user.user.dto.UserDto;
import com.example.bootagit_project01.user.user.entity.User;
import com.example.bootagit_project01.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(propagation = Propagation.REQUIRED) // JPA 가 Join 들어올때 모든 데이터변경이 트랜잭션 안에서 실행됨
@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

//    public User JoinUser(UserDto userDTO) {
//        User user = new User();
//        user.setUsername(userDTO.getPassword());
//        user.setPassword(userDTO.getPassword());
//        user.setEmail(userDTO.getEmail());
//
//        validateDuplicateUser(user);    // 중복 회원 검증
//
//
//        return userRepository.save(user);
//    }


    public UserDto joinUser(UserDto userDto) {
        User user = User.builder()
                .userid(userDto.getUserid())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))

                .email(userDto.getEmail())
                .build();

        validateDuplicateUser(user);

        User savedUser = userRepository.save(user);



        return convertToUserDto(savedUser);

        // 비밀번호 인코딩해서 db 에 암호화 하는 작업 추가



    }


    private void validateDuplicateUser(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }


    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> findUserId(Long userId) {
        return userRepository.findById(userId)
                .map(this::convertToUserDto);
    }

    public UserDto updateUser(Long userid, UserDto userDto) {
        User existingUser = userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("해당하는 아이디를 찾을 수 없습니다."));

        existingUser.setUsername(userDto.getUsername());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(existingUser);
        return convertToUserDto(updatedUser);
    }

    public void deleteUser(Long userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));

        userRepository.deleteByUserid(user.getUserid());
    }

    // ModelMapper 대신 직접 User 엔티티에서 UserDto로 변환하는 메서드 convertToUserDto
    private UserDto convertToUserDto(User user) {
        return UserDto.builder()
                .userid(user.getUserid())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
    }
