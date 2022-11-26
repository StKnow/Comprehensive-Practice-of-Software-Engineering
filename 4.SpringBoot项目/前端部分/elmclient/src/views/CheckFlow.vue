<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>交易流水</p>
    </header>
    <!-- 查询部分 -->
    <div class="shop-detail">
      <div class="tab-comments">
        <div class="shop-comments-wrap">
          <div v-for="item in transFlowArr" class="item">
            <div class="comment">
              <div class="text">交易流水ID: {{ item.transactionId }}</div>
              <div class="text">交易时间: {{ item.transactionDate }}</div>
              <div class="text">交易金额: {{ item.transactionAmount }}</div>
              <div class="text">交易类型: {{ item.transactionType }}</div>
              <div class="text">入账钱包账号: {{ item.toWalletId }}</div>
              <div class="text">出账钱包账号: {{ item.fromWalletId }}</div>
            </div>
          </div>
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
  data() {
    return {
      transFlowArr: [],
      user: {},
    };
  },
  components: {
    Footer,
  },
  created() {
    this.user = this.$getSessionStorage("user");
    this.$axios
      .post(
        "WalletController/listCheckFlowByUserId",
        this.$qs.stringify({
          userId: this.user.userId,
        })
      )
      .then((response) => {
        this.transFlowArr = response.data;
        console.log(response.data)
      })
      .catch((error) => {
        console.error(error);
      });
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
/****************** 查询部分 ******************/
.wrapper .shop-detail {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
}
.wrapper .shop-detail .tab-comments {
  width: 100%;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
}
.wrapper .shop-detail .tab-comments .shop-comments-wrap {
  position: relative;
  z-index: 1;
  min-height: 100%;
  flex: 1;
  box-sizing: border-box;
  padding: 0.5rem 0.75rem 0.6875rem;
  display: flex;
  flex-direction: column;
  background: #f5f5f5 linear-gradient(180deg, #fbfbfb 0%, #f5f5f5 0.625rem);
}
.wrapper .shop-detail .tab-comments .shop-comments-wrap .item {
  margin-top: 12vw;
  padding: 0.5rem 0.75rem;
  background-color: #ffffff;
  margin-bottom: 0.5rem;
  border-radius: 0.5rem;
  width: 21rem;
  box-sizing: border-box;
  --mainColor: #02b6fd;
  --lightColor: #e6f8ff;
  --lightBgColor: rgba(2, 182, 253, 0.08);
}
.wrapper .shop-detail .tab-comments .shop-comments-wrap .comment {
  margin-top: -0.25rem;
}
.wrapper .shop-detail .tab-comments .shop-comments-wrap .comment .text {
  line-break: anywhere;
  word-break: break-word;
}
</style>