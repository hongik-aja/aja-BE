package mutsa_aegeodon.aja.service;

import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.config.jwt.JwtUtil;
import mutsa_aegeodon.aja.entity.User;
import mutsa_aegeodon.aja.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${spring.kakao.client-id}")
    private String clientId;

    @Value("${spring.kakao.redirect-uri}")
    private String redirectUri;

    public String kakaoLogin(String code) {

        // 1. Access Token 요청
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(tokenUrl, tokenRequest, Map.class);
        String accessToken = (String) tokenResponse.getBody().get("access_token");

        // 2. 사용자 정보 요청
        HttpHeaders infoHeaders = new HttpHeaders();
        infoHeaders.setBearerAuth(accessToken);

        HttpEntity<Void> infoRequest = new HttpEntity<>(infoHeaders);
        ResponseEntity<Map> userInfoResponse = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                infoRequest,
                Map.class
        );

        Map<String, Object> kakaoAccount = (Map<String, Object>) userInfoResponse.getBody().get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String kakaoId = String.valueOf(userInfoResponse.getBody().get("id"));
        String nickname = (String) profile.get("nickname");
        String profileImage = (String) profile.get("profile_image_url");

        // 3. 사용자 DB 확인 및 생성
        User user = findOrCreateUser(kakaoId, nickname, profileImage);

        // 4. JWT 발급
        return jwtUtil.generateToken(user.getKakaoId(), jwtUtil.getAccessTokenExpiration(), "KAKAO");    }

    private User findOrCreateUser(String kakaoId, String nickname, String profileImage) {
        return userRepository.findByKakaoId(kakaoId)
                .orElseGet(() -> {
                    LocalDateTime now = LocalDateTime.now();
                    User user = User.builder()
                            .kakaoId(kakaoId)
                            .nickname(nickname)
                            .profileImage(profileImage)
                            .createdAt(now)
                            .updatedAt(now)
                            .build();
                    return userRepository.save(user);
                });
        // repository.findByKakaoId()로 조회
        // 없으면 새로 생성하고 저장
        // 리턴
    }
}