package mutsa_aegeodon.aja.service;

import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.config.jwt.JwtUtil;
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
    private final JwtUtil jwtUtil; // ✅ 주입 추가

    public Long createUser(UserRequestDto userRequestDto) {
        User user = userRepository.findById(userRequestDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return userRepository.save(user).getUserId();
    }

    public User getUserFromToken(String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String kakaoId = jwtUtil.getSocialIdFromToken(token);

        return userRepository.findByKakaoId(kakaoId) // <- 이 메서드가 있어야 함!
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public String login(User user) {
        return jwtUtil.generateAccessToken(user.getKakaoId());
    }
}