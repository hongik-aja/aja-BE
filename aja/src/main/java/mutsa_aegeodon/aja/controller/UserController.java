package mutsa_aegeodon.aja.controller;


import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.config.jwt.JwtUtil;
import mutsa_aegeodon.aja.dto.request.UserRequestDto;
import mutsa_aegeodon.aja.entity.User;
import mutsa_aegeodon.aja.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //유저 생성
    @PostMapping("/user")
    public ResponseEntity<Long> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }

    @GetMapping("/api/user/me")
    public ResponseEntity<?> getMyInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        User user = userService.getUserFromToken(token);

        return ResponseEntity.ok().body(Map.of(
                "nickname", user.getNickname(),
                "profileImage", user.getProfileImage()
        ));
    }
}