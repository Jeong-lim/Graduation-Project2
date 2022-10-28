package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscibeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscibeRepository subscibeRepository;

    @Transactional
    public void 구독하기(int fromUserId, int toUserId) {

    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId) {

    }
}
