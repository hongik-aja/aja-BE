package mutsa_aegeodon.aja.controller;

import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.service.KakaoAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KakaoAuthController {

    private final KakaoAuthService kakaoAuthService;

    @PostMapping("/api/auth/kakao/callback")
    public ResponseEntity<?> kakaoCallback(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        String jwtToken = kakaoAuthService.kakaoLogin(code);
        return ResponseEntity.ok().body(Map.of("token", jwtToken));
    }
}
