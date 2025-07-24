package mutsa_aegeodon.aja.service;

import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.dto.request.UserRequestDto;
import mutsa_aegeodon.aja.entity.User;
import mutsa_aegeodon.aja.exception.CustomException;
import mutsa_aegeodon.aja.exception.ErrorCode;
import mutsa_aegeodon.aja.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long createUser(UserRequestDto userRequestDto) {
        //User 중복 확인코드
        User user = userRepository.findById(userRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return userRepository.save(user).getUserId();
    }
}
