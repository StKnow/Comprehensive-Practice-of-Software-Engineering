package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.domain.VirtualWalletTransaction;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.VirtualWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/WalletController")
public class VirtualWalletController {
    @Autowired
    private VirtualWalletService virtualWalletService;

    @RequestMapping("/recharge")
    public int recharge(Integer walletId, Double amount) throws Exception {
        System.out.println("-----------"+walletId+"   "+amount);
        return virtualWalletService.recharge(walletId, amount);
    }

    @RequestMapping("/withdrawal")
    public int withdrawal(Integer walletId, Double amount) throws Exception {
        return virtualWalletService.withdrawal(walletId,amount);
    }

    @RequestMapping("/getBalanceById")
    public Double getBalanceById(Integer walletId) {
        return virtualWalletService.getBalanceById(walletId);
    }

    @RequestMapping("listCheckFlowByUserId")
    public List<VirtualWalletTransaction> listCheckFlowByUserId(String userId) {
        return virtualWalletService.listCheckFlowByUserId(userId);
    }
}
