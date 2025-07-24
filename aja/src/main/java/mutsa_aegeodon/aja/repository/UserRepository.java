package mutsa_aegeodon.aja.repository;

import mutsa_aegeodon.aja.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}

