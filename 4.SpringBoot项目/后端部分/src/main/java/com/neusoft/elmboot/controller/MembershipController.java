package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.po.Membership;
import com.neusoft.elmboot.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MembershipController")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @RequestMapping("/saveMembership")
    public int saveMembership(Membership membership) throws Exception {
        return membershipService.saveMembership(membership);
    }

    @RequestMapping("/getMembershipById")
    public int getMembershipById(String userId) throws Exception {
        return membershipService.getMembershipById(userId);
    }
}
