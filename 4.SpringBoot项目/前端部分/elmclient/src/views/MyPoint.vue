<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>我的积分</p>
    </header>
    <!-- 钱包部分 -->
    <div>
      <img src="../assets/积分.png" alt="钱包" />
      <div class="balance">积分: {{ point }}</div>
    </div>
    <div class="function">
      <li @click="toPointsMall">
        <img src="../assets/积分商城.png" alt="积分商城" />
        <p>积分商城</p>
      </li>
      <li @click="toPointFlow">
        <img src="../assets/查询.png" alt="积分明细" />
        <p>积分明细</p>
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
      point: 0,
    };
  },
  components: {
    Footer,
  },
  created() {
    this.user = this.$getSessionStorage("user");
    this.$axios
      .post("IntegralController/getPointsById", this.$qs.stringify(this.user))
      .then((response) => {
        this.point = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    toPointsMall() {
      this.$router.push({
        path: "/pointsMall",
      });
    },
    toPointFlow() {
      this.$router.push({
        path: "/pointFlow",
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
  margin-top: 5vw;
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