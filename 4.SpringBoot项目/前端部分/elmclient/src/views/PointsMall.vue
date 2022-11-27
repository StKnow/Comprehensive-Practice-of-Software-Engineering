<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>积分商城</p>
    </header>
    <!-- 商城部分 -->
    <div class="function">
      <li>
        <img src="../assets/小熊玩偶.jpg" alt="小熊玩偶" />
        <input type="radio" style="width: 6vw; height: 4.5vw" name="奖品" v-model="pointNum" value="0">1000积分</input>
      </li>
      <li>
        <img src="../assets/马克杯.jpg" alt="马克杯" />
        <input type="radio" style="width: 6vw; height: 4.5vw" name="奖品" v-model="pointNum" value="1">800积分</input>
      </li>
      <li>
        <img src="../assets/帆布袋.jpg" alt="帆布袋" />
        <input type="radio" style="width: 6vw; height: 4.5vw" name="奖品" v-model="pointNum" value="2">500积分</input>
      </li>
    </div>
    </ul>
    <div class="payment-button">
      <button @click="payPoint">积分兑换</button>
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
      pointNum: 0,
    };
  },
    created() {
    this.user = this.$getSessionStorage("user");
  },
  components: {
    Footer,
  },
  methods: {
    payPoint() {
      if (this.pointNum == 0) {
        this.point = 1000;
      } else if (this.pointNum == 1) {
        this.point = 800;
      } else if (this.pointNum == 2) {
        this.point = 500;
      } else {
        alert("请选择商品");
        return;
      }
      this.$axios
        .post(
          "IntegralController/payPoints",
          this.$qs.stringify({
            userId: this.user.userId,
            point: this.point,
          })
        )
        .then((response) => {
          if (response.data > 0) {
            alert("积分兑换成功！");
            this.$router.go(-1);
          } else {
            alert("积分兑换失败！");
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
/****************** 商城部分 ******************/
.wrapper .function {
  margin-top: 10vw;
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
.wrapper .payment-button button {
  width: 100%;
  height: 10vw;
  border: none;
  /*去掉外轮廓线*/
  outline: none;
  border-radius: 4px;
  background-color: #38ca73;
  color: #fff;
}
</style>