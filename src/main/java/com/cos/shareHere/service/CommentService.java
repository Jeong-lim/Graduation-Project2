package com.cos.shareHere.service;

import com.cos.shareHere.domain.comment.Comment;
import com.cos.shareHere.domain.comment.CommentRepository;
import com.cos.shareHere.domain.image.Image;
import com.cos.shareHere.domain.user.User;
import com.cos.shareHere.domain.user.UserRepository;
import com.cos.shareHere.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment 댓글쓰기(String content, Integer imageId, Integer userId) {

        // ID만 가지고 있는 가짜 객체 생성
        Image image = new Image();
        image.setId(imageId);
        
        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setImage(image);
        comment.setUser(userEntity);

        return commentRepository.save(comment);
    }

    @Transactional
    public void 댓글삭제(Integer id) {
        try {
            System.out.println("commentId : " + id);
            commentRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomApiException(e.getMessage());
        }
    }
    
}
