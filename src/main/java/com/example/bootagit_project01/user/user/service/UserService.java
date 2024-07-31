package com.example.bootagit_project01.user.user.service;

import com.example.bootagit_project01.user.user.dto.UserDto;
import com.example.bootagit_project01.user.user.entity.User;
import com.example.bootagit_project01.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional // JPA 가 Join 들어올때 모든 데이터변경이 트랜잭션 안에서 실행됨
@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;


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
        // UserDto를 User 엔티티로 변환
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        // 중복 회원 검증
        validateDuplicateUser(user);

        // 사용자 저장
        User savedUser = userRepository.save(user);

        // 저장된 사용자 정보를 UserDto로 반환
        return UserDto.builder()
                .userid(savedUser.getUserid())
                .username(savedUser.getUsername())
                .password(savedUser.getPassword())
                .email(savedUser.getEmail())
                .build();
    }


    private void validateDuplicateUser(User user) {
        userRepository.findByName(user.getUsername())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }


    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public Optional<UserDto> findUserId(Long userId) {
        return userRepository.findById(userId)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    public UserDto updateUser(Long userid, UserDto userDto) {
        User existingUser = userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("해당하는 아이디를 찾을 수 없습니다."));

        modelMapper.map(userDto, existingUser);

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    public void deleteUser(Long userid) {
        User deleteduser = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));

        userRepository.deleteByUserid(userid);
    }
}
