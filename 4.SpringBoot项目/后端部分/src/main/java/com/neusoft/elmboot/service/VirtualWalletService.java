package com.neusoft.elmboot.service;

import com.neusoft.elmboot.domain.VirtualWalletTransaction;

import java.util.List;

public interface VirtualWalletService {
    //银行账户业务
    public int recharge(Integer walletId, Double amount);

    public int withdrawal(Integer walletId, Double amount);

    //虚拟钱包业务
    public Double getBalanceById(Integer walletId);

    public void transfer(Integer fromWalletId, Integer toWalletId, Double amount);

    public List<VirtualWalletTransaction> listCheckFlowByUserId(String userId);
}
