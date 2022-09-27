package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.Membership;

public interface MembershipService {
    public int saveMembership(Membership membership);
    public Integer getMembershipById(String userId);
}
