<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>会员注册</p>
    </header>
    <!-- 表单部分 -->
    <ul class="form-box">
      <li>
        <div class="content" style="font-size: 5vw">
          <input
            type="radio"
            v-model="user.userMembership.grade"
            value="1"
            style="width: 6vw; height: 4.5vw"
          /><img src="../assets/白银会员.png" />白银会员 &#165;10<br />
          <input
            type="radio"
            v-model="user.userMembership.grade"
            value="2"
            style="width: 6vw; height: 4.5vw"
          /><img src="../assets/黄金会员.png" />黄金会员 &#165;20<br />
          <input
            type="radio"
            v-model="user.userMembership.grade"
            value="3"
            style="width: 6vw; height: 4.5vw"
          /><img src="../assets/钻石会员.png" />钻石会员 &#165;30<br />
        </div>
      </li>
    </ul>
    <div class="button-login">
      <button @click="membershipRegister">去支付</button>
    </div>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";
export default {
  name: "MembershipRegister",
  data() {
    return {
      user: {},
      userMembership: {
        grade: 0,
      },
    };
  },
  methods: {
    membershipRegister() {
      this.user = this.$getSessionStorage("user");
      //注册请求
      this.$axios
        .post(
          "MembershipController/saveMembership",
          this.$qs.stringify({
            userId: this.user.userId,
            grade: this.userMembership.grade,
          })
        )
        .then((response) => {
          if (response.data > 0) {
            alert("注册会员成功！");
            this.$router.go(-1);
          } else {
            alert("注册会员失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  components: {
    Footer,
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
/****************** 表单部分 ******************/
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
.wrapper .form-box li .content {
  flex: 1;
}
.wrapper .form-box li .content input {
  border: none;
  outline: none;
  width: 100%;
  height: 4vw;
  font-size: 8vw;
}
.wrapper .form-box li .content img {
  width: 6vw;
  height: 6vw;
  margin-right: 2vw;
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
.wrapper .button-register {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
}
.wrapper .button-register button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;
  color: #666;
  background-color: #eee;
  border-radius: 4px;
  border: none;
  outline: none;
  border: solid 1px #ddd;
}
</style>