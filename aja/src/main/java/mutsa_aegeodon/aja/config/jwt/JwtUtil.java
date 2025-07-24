package mutsa_aegeodon.aja.config.jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mutsa_aegeodon.aja.entity.User;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

public class JwtUtil {

    private static Key secretKey;
    @Value("${jwt.secret}")
    private String secret;

    // 토큰 유효 기간 (예: 7일)
    private static final long EXPIRATION_TIME_MS = Duration.ofDays(7).toMillis();

    // 토큰 생성
    public static String createToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_TIME_MS);

        return Jwts.builder()
                .setSubject(user.getUserId().toString()) // userId가 Long 타입
                .claim("nickname", user.getNickname())
                .claim("profileImage", user.getProfileImage())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // (선택) 토큰에서 사용자 ID 추출
    public static Long getUserIdFromToken(String token) {
        return Long.parseLong(
                Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject()
        );
    }

    // (선택) 토큰 유효성 검증
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}