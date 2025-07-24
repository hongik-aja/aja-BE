package mutsa_aegeodon.aja.service;


import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.entity.Category;
import mutsa_aegeodon.aja.entity.Post;
import mutsa_aegeodon.aja.entity.User;
import mutsa_aegeodon.aja.repository.CategoryRepository;
import mutsa_aegeodon.aja.repository.PostRepository;
import mutsa_aegeodon.aja.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Post createPost(Long userId, Long categoryId, String content){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        Post post = Post.builder()
                .user(user)
                .category(category)
                .content(content)
                .build();

        return postRepository.save(post);
    }
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    /**
     * 전체 게시글 목록 조회
     */
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
