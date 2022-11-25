<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>我的钱包</p>
    </header>
    <!-- 钱包部分 -->
    <div>
      <img src="../assets/钱包.png" alt="钱包" />
      <div class="balance">余额：10元</div>
    </div>
    <div class="function">
      <li @click="toRecharge">
        <img src="../assets/充值.png" alt="充值" />
        <p>充值</p>
      </li>
      <li @click="toWithdrawal">
        <img src="../assets/提现.png" alt="提现" />
        <p>提现</p>
      </li>
      <li @click="toCheckFlow">
        <img src="../assets/查询.png" alt="查询交易流水" />
        <p>查询交易流水</p>
      </li>
    </div>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";
export default {
  data() {
    return {
      user: {},
      balance: 0,
    };
  },
  components: {
    Footer,
  },
  created() {
    this.user = this.$getSessionStorage("user");

    this.$axios
      .post("WalletController/getBalanceById", this.$qs.stringify(this.user))
      .then((response) => {
        this.balance = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    toRecharge() {
      this.$router.push({
        path: "/recharge",
      });
    },
    toWithdrawal() {
      this.$router.push({
        path: "/withdrawal",
      });
    },
    toCheckFlow() {
      this.$router.push({
        path: "/checkFlow",
      });
    },
  },
};
</script>
<style scoped>
/****************** 总容器 ******************/
.wrapper {
  width: 100%;
  height: 100%;
}
/****************** header部分 ******************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097ff;
  color: #fff;
  font-size: 4.8vw;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
  display: flex;
  justify-content: center;
  align-items: center;
}
/****************** 钱包部分 ******************/
.wrapper div img {
  width: 20vw;
  height: 20vw;
  margin-top: 20vw;
  margin-left: 40vw;
}
.wrapper div .balance {
  margin-left: 40vw;
}
.wrapper .function {
  width: 100%;
  height: 48vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  /*要使用align-content。10个子元素将自动换行为两行，而且两行作为一个整体垂直居中*/
  align-content: center;
}
.wrapper .function li {
  width: 33vw;
  height: 20vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  user-select: none;
  cursor: pointer;
}
.wrapper .function li img {
  width: 15vw;
  height: 15vw;
  margin-top: 0vw;
  margin-left: 0vw;
}
.wrapper .function li p {
  margin-top: 2vw;
  font-size: 4vw;
  color: #666;
}
</style>