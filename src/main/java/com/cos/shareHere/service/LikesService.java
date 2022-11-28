package com.cos.shareHere.service;

import com.cos.shareHere.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void 좋아요(Integer imageId, Integer principalId) {
        likesRepository.mLikes(imageId, principalId);
    }

    @Transactional
    public void 좋아요취소(Integer imageId, Integer principalId) {
        likesRepository.mUnLikes(imageId, principalId);
    }
    
}
