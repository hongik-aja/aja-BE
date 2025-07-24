package mutsa_aegeodon.aja.repository;

import mutsa_aegeodon.aja.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
