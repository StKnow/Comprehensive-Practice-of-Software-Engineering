package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.domain.VirtualWallet;
import com.neusoft.elmboot.domain.VirtualWalletTransaction;
import com.neusoft.elmboot.exception.InsufficientBalanceException;
import com.neusoft.elmboot.exception.InvalidAmountException;
import com.neusoft.elmboot.mapper.VirtualWalletMapper;
import com.neusoft.elmboot.mapper.VirtualWalletTransactionMapper;
import com.neusoft.elmboot.service.VirtualWalletService;
import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirtualWalletServiceImpl implements VirtualWalletService {
    @Autowired
    private VirtualWalletMapper virtualWalletMapper;
    @Autowired
    private VirtualWalletTransactionMapper virtualWalletTransactionMapper;

    @Override
    public int recharge(Integer walletId, Double amount) {
        VirtualWallet virtualWallet = new VirtualWallet();
        virtualWallet.setWalletId(walletId);
        virtualWallet.setBalance(virtualWalletMapper.getBalanceById(walletId));
        try {
            virtualWallet.credit(amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        int flag1 = virtualWalletMapper.updateBalance(walletId, virtualWallet.getBalance());

        VirtualWalletTransaction virtualWalletTransaction = new VirtualWalletTransaction();
        virtualWalletTransaction.setTransactionDate(CommonUtil.getCurrentDate());
        virtualWalletTransaction.setTransactionAmount(amount);
        virtualWalletTransaction.setTransactionType("充值");
        virtualWalletTransaction.setToWalletId(walletId);
        virtualWalletTransaction.setFromWalletId(null);
        virtualWalletTransaction.setUserId(virtualWalletMapper.getUserIdByWalletId(walletId));
        int flag2 = virtualWalletTransactionMapper.saveCheckFlow(virtualWalletTransaction);

        return flag1 & flag2;
    }

    @Override
    public int withdrawal(Integer walletId, Double amount) {
        VirtualWallet virtualWallet = new VirtualWallet();
        virtualWallet.setWalletId(walletId);
        virtualWallet.setBalance(virtualWalletMapper.getBalanceById(walletId));
        try {
            virtualWallet.debit(amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        int flag1 = virtualWalletMapper.updateBalance(walletId, virtualWallet.getBalance());

        VirtualWalletTransaction virtualWalletTransaction = new VirtualWalletTransaction();
        virtualWalletTransaction.setTransactionDate(CommonUtil.getCurrentDate());
        virtualWalletTransaction.setTransactionAmount(amount);
        virtualWalletTransaction.setTransactionType("提现");
        virtualWalletTransaction.setToWalletId(null);
        virtualWalletTransaction.setFromWalletId(walletId);
        virtualWalletTransaction.setUserId(virtualWalletMapper.getUserIdByWalletId(walletId));
        int flag2 = virtualWalletTransactionMapper.saveCheckFlow(virtualWalletTransaction);

        return flag1 & flag2;
    }

    @Override
    public Double getBalanceById(Integer walletId) {
        return virtualWalletMapper.getBalanceById(walletId);
    }

    @Override
    public void transfer(Integer fromWalletId, Integer toWalletId, Double amount) {
        //用户钱包
        VirtualWallet userWallet = new VirtualWallet();
        userWallet.setWalletId(fromWalletId);
        userWallet.setBalance(virtualWalletMapper.getBalanceById(fromWalletId));
        //商家钱包
        VirtualWallet businessWallet = new VirtualWallet();
        businessWallet.setWalletId(toWalletId);
        businessWallet.setBalance(virtualWalletMapper.getBalanceById(toWalletId));
        try {
            userWallet.debit(amount);
            businessWallet.credit(amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        virtualWalletMapper.updateBalance(fromWalletId, userWallet.getBalance());
        virtualWalletMapper.updateBalance(toWalletId, businessWallet.getBalance());

        VirtualWalletTransaction virtualWalletTransaction = new VirtualWalletTransaction();
        virtualWalletTransaction.setTransactionDate(CommonUtil.getCurrentDate());
        virtualWalletTransaction.setTransactionAmount(amount);
        virtualWalletTransaction.setTransactionType("交易");
        virtualWalletTransaction.setToWalletId(toWalletId);
        virtualWalletTransaction.setFromWalletId(fromWalletId);
        virtualWalletTransaction.setUserId(virtualWalletMapper.getUserIdByWalletId(fromWalletId));
        virtualWalletTransactionMapper.saveCheckFlow(virtualWalletTransaction);
    }

    @Override
    public List<VirtualWalletTransaction> listCheckFlowByUserId(String userId) {
        return virtualWalletTransactionMapper.listCheckFlowByUserId(userId);
    }
}
