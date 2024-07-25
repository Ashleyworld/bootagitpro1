package com.example.bootagit_project01.user.service;

import com.example.bootagit_project01.user.dto.UserDto;
import com.example.bootagit_project01.user.entity.User;
import com.example.bootagit_project01.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
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

    // 회원가입
//    public UserDto joinUser(UserDto userDto) {
//        // 같은 이름이 있는 중복 회원x
//        // 코드안에 Optional 이 들어간게 예쁘지 않음으로
////            Optional<User> result = userRepository.findByName(user.getUsername());
////            result.ifPresent(u -> {
//
//        //Optional 안에 내장 메서드 ifPresent() 만약에 값이 있으면->
//        // 값을 그냥 꺼내는 것 보다 위의 예외처리 권장/ or문 권장(orElseThrow)
//        User user = new User();
//        user.setUsername(userDto.getUsername());
//        user.setPassword(userDto.getPassword());
//        user.setEmail(userDto.getEmail());
//
//        validateDuplicateUser(user);    // 중복 회원 검증
//
////        User savedUser = userRepository.save(user);
////        return savedUser.getUserid();
//
//        // 사용자 저장
//        User savedUser = userRepository.save(user);
//
//        // 저장된 사용자 정보를 UserDto로 직접 변환하여 반환
//        UserDto responseDto = new UserDto();
//        responseDto.setUsername(savedUser.getUsername());
//        responseDto.setPassword(savedUser.getPassword());
//        responseDto.setEmail(savedUser.getEmail());
//
//        return responseDto;
//
//    }


    public UserDto joinUser(UserDto userDto) {
        // UserDto를 User 객체로 변환
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        // 중복 회원 검증
        validateDuplicateUser(user);

        // 사용자 저장
        User savedUser = userRepository.save(user);

        // 저장된 사용자 정보를 UserDto로 직접 설정
        userDto.setUserid(savedUser.getUserid());
        userDto.setUsername(savedUser.getUsername());
        userDto.setPassword(savedUser.getPassword());
        userDto.setEmail(savedUser.getEmail());
        return userDto;
    }


    private void validateDuplicateUser(User user) {
        userRepository.findByName(user.getUsername())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }

    /*전체 회원 조회*/
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long userId){
        return userRepository.findById(userId);
    }

    // 회원 삭제
    public void deleteUser(Long userid){
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));

        userRepository.deleteById(user);
    }


    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(Long userid, UserDto userDto){
        User existingUser = userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("해당하는 아이디를 찾을 수 없습니다."));

        modelMapper.map(userDto, existingUser);

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }
}
