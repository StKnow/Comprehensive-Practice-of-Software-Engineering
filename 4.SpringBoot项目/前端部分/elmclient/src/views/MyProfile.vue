<template>
  <div class="wrapper">
    <!-- 用户信息部分 -->
    <div class="userinformation">
      <div class="userimg">
        <img src="../assets/头像.png" style="width: 60px; herght: 60px" />
      </div>
      <div class="usernameandid">
        <p class="username">
          {{ user.userName }}{{ user.userSex | sexFilter }}
        </p>
        <p class="userid">尊贵的{{ grade | gradeNameFilter }}会员</p>
        <p class="userid">{{ user.userId }}</p>
      </div>
    </div>
    <div class="littleitem">
      <div class="littleitembutton">
        <aside class="littleitemimg">
          <img src="../assets/地址.png" alt="地址" />
        </aside>
        <div class="word" @click="toUserAddress">
          我的地址
          <span class="right">
            <img src="../assets/向右.png" alt="向右" />
          </span>
        </div>
      </div>
    </div>
    <div class="littleitem">
      <div class="littleitembutton">
        <aside class="littleitemimg">
          <img src="../assets/黄金会员.png" alt="会员" />
        </aside>
        <div class="word" @click="toMembershipRegister">
          注册会员
          <span class="right">
            <img src="../assets/向右.png" alt="向右" />
          </span>
        </div>
      </div>
    </div>
    <div class="littleitem">
      <div class="littleitembutton">
        <aside class="littleitemimg">
          <img src="../assets/钱包.png" alt="钱包" />
        </aside>
        <div class="word" @click="toMyWallet">
          我的钱包
          <span class="right">
            <img src="../assets/向右.png" alt="向右" />
          </span>
        </div>
      </div>
    </div>
    <div class="littleitem">
      <div class="littleitembutton">
        <aside class="littleitemimg">
          <img src="../assets/积分商城.png" alt="积分商城" />
        </aside>
        <div class="word" @click="toPointsMall">
          我的积分
          <span class="right">
            <img src="../assets/向右.png" alt="向右" />
          </span>
        </div>
      </div>
    </div>

    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";
export default {
  name: "MyProfile",
  data() {
    return {
      user: {},
      grade: 0,
      bonus: 0,
      walletId: 0,
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");

    //查询当前用户信息
    this.$axios
      .post(
        "MembershipController/getMembershipById",
        this.$qs.stringify(this.user)
      )
      .then((response) => {
        this.grade = response.data;
      })
      .catch((error) => {
        console.error(error);
      });

      this.$axios
      .post(
        "UserController/getWalletIdByUserId",
        this.$qs.stringify(this.user),
      )
      .then((response) => {
        this.walletId = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  filters: {
    sexFilter(value) {
      return value == 1 ? "先生" : "女士";
    },
    gradeNameFilter(grade) {
      if (grade == 0) return "普通";
      else if (grade == 1) return "白银";
      else if (grade == 2) return "黄金";
      else return "钻石";
    },
  },
  methods: {
    toUserAddress() {
      this.$router.push({
        path: "/userAddress",
      });
    },
    toMembershipRegister() {
      this.$router.push({
        path: "/membershipRegister",
      });
    },
    toMyWallet() {
      this.$router.push({
        path: "/myWallet",
        query: { walletId: this.walletId },
      });
    },
    toPointsMall() {
      this.$router.push({
        path: "/myPoint",
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
/****************** 用户信息部分 ******************/
.wrapper .userinformation {
  display: flex;
  background-image: linear-gradient(90deg, #0af, #0085ff);
  padding: 0.666667rem 0.4rem;
  padding: 6.666667vw 4vw;
  color: #fff;
  -webkit-align-items: center;
  align-items: center;
}
.wrapper .userinformation .userimg img {
  border-radius: 50%;
}
.wrapper .userinformation .usernameandid {
  overflow: hidden;
  margin-left: 0.48rem;
  margin-left: 4.8vw;
  -webkit-flex-grow: 1;
  flex-grow: 1;
}
.wrapper .userinformation .usernameandid .username {
  font-size: 5vw;
  margin-bottom: 0.213333rem;
  margin-bottom: 2.133333vw;
  font-weight: 700;
  display: -webkit-flex;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
}
.wrapper .userinformation .usernameandid .userid {
  display: -webkit-flex;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  font-size: 0.32rem;
}
/****************** 表项部分 ******************/
.wrapper .littleitem {
  margin-top: 0.266667rem;
  margin-top: 2.666667vw;
  border-top: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
  font-size: 0.426667rem;
  line-height: 0.453333rem;
  line-height: 4.533333vw;
  background: rgba(172, 169, 169, 0.815);
}
.wrapper .littleitem .littleitembutton {
  font-size: 4.8vw;
  color: #333;
  display: -webkit-flex;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  padding-left: 0.666667rem;
  padding-left: 6.666667vw;
}
.wrapper .littleitem .littleitembutton .littleitemimg {
  margin-left: -0.266667rem;
  margin-left: -2.666667vw;
  margin-right: 0.266667rem;
  margin-right: 2.666667vw;
}
.wrapper .littleitem .littleitembutton .littleitemimg img {
  width: 7vw;
  height: 7vw;
}
.wrapper .littleitem .littleitembutton .word {
  display: -webkit-flex;
  display: flex;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  width: 100%;
  padding: 0.373333rem 0.266667rem 0.373333rem 0;
  padding: 3.733333vw 2.666667vw 3.733333vw 0;
}
.wrapper .littleitem .littleitembutton .word .right {
  font-size: 0.426667rem;
  color: #333;
  display: -webkit-flex;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  padding-left: 0.666667rem;
  padding-left: 6.666667vw;
}
.wrapper .littleitem .littleitembutton .word .right img {
  width: 0.266667rem;
  width: 2.666667vw;
  height: 0.266667rem;
  height: 2.666667vw;
  fill: #bbb;
}
</style>