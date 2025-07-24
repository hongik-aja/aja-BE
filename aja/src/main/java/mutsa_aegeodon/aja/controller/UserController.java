package mutsa_aegeodon.aja.controller;


import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.dto.request.UserRequestDto;
import mutsa_aegeodon.aja.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //유저 생성
    @PostMapping("/user")
    public ResponseEntity<Long> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }
}