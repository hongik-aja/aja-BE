package mutsa_aegeodon.aja.controller;

import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.entity.Post;
import mutsa_aegeodon.aja.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor

public class PostController {
    private final PostService postService;

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> createPost(
            @PathVariable Long userId,
            @RequestParam Long categoryId,
            @RequestParam String content
    ) {
        Post post = postService.createPost(userId, categoryId, content);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }



}
