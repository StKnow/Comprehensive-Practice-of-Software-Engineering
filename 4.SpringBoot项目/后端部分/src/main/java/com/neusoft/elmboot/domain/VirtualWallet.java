package com.neusoft.elmboot.domain;

import com.neusoft.elmboot.exception.InsufficientBalanceException;
import com.neusoft.elmboot.exception.InvalidAmountException;

public class VirtualWallet {
    private Integer walletId;
    private Double balance;

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    public void debit(Double amount) {
        if (this.balance < amount) {
            throw new InsufficientBalanceException("钱包余额不足");
        }
        this.balance -= amount;
    }

    public void credit(Double amount) {
        if(amount<0) {
            throw new InvalidAmountException("存入金额错误");
        }
        this.balance += amount;
    }
}
