package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.po.Membership;
import com.neusoft.elmboot.po.User;
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
    public Integer getMembershipById(User user) throws Exception {
        return membershipService.getMembershipById(user.getUserId());
    }

    @RequestMapping("/updateMembership")
    public int updateMembership(Membership membership) throws Exception {
        return membershipService.updateMembership(membership)
;    }
}
