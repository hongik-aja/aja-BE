package mutsa_aegeodon.aja.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private Long userId;
    private String kakaoId;
    private String nickname;
    private String profileImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
