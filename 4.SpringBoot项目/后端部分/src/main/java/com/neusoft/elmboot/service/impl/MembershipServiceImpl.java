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
    public Integer getMembershipById(String userId) {
        if (membershipMapper.getMembershipById(userId) == null) {
            return 0;
        } else {
            return membershipMapper.getMembershipById(userId);
        }
    }

    @Override
    public int updateMembership(Membership membership) {
        return membershipMapper.updateMembership(membership);
    }
}
