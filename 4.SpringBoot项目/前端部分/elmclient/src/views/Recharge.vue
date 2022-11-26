<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>钱包充值</p>
    </header>
    <!-- 充值部分 -->
    <ul class="form-box">
      <li>
        <div class="title">金额：</div>
        <div class="content">
          <input type="text" v-model="amount" placeholder="金额" />
        </div>
      </li>
    </ul>
    <div class="button-login">
      <button @click="recharge">充值</button>
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
      outAccount: "",
      amount: "",
      user: {},
      walletId: "",
    };
  },
  components: {
    Footer,
  },
  created() {
    this.user = this.$getSessionStorage("user");
    this.$axios
      .post(
        "UserController/getWalletIdByUserId",
        this.$qs.stringify(this.user.userId)
      )
      .then((response) => {
        this.walletId = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    recharge() {
      this.$axios
        .post(
          "WalletController/recharge",
          this.$qs.stringify({
            walletId: this.walletId,
            amount: this.amount,
          })
        )
        .then((response) => {
          if (response.data > 0) {
            alert("充值成功！");
            this.$router.go(-1);
          } else {
            alert("充值失败！");
          }
        })
        .catch((error) => {
          console.error(error);
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
/****************** 充值部分 ******************/
.wrapper .form-box {
  width: 100%;
  margin-top: 12vw;
}
.wrapper .form-box li {
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
  display: flex;
  align-items: center;
}
.wrapper .form-box li .title {
  flex: 0 0 18vw;
  font-size: 3vw;
  font-weight: 700;
  color: #666;
}
.wrapper .form-box li .content {
  flex: 1;
}
.wrapper .form-box li .content input {
  border: none;
  outline: none;
  width: 100%;
  height: 4vw;
  font-size: 3vw;
}
.wrapper .button-login {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
}
.wrapper .button-login button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;
  color: #fff;
  background-color: #38ca73;
  border-radius: 4px;
  border: none;
  outline: none;
}
</style>