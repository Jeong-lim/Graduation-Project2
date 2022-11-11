package com.cos.shareHere.service;

import com.cos.shareHere.domain.subscribe.SubscibeRepository;
import com.cos.shareHere.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscibeRepository subscibeRepository;

    @Transactional
    public void 구독하기(int fromUserId, int toUserId) {
        try {
            subscibeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId) {
        try {
            subscibeRepository.mUnSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 취소하였습니다.");
        }
    }
}