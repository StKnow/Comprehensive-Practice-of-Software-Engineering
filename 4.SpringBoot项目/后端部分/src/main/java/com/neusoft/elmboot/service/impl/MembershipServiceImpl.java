package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.mapper.MembershipMapper;
import com.neusoft.elmboot.po.Membership;
import com.neusoft.elmboot.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    private MembershipMapper membershipMapper;

    @Override
    public int saveMembership(Membership membership) {
        return membershipMapper.saveMembership(membership);
    }

    @Override
    public int getMembershipById(String userId) {
        return membershipMapper.getMembershipById(userId);
    }
}
