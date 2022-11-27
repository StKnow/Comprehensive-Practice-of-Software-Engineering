<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>在线支付</p>
    </header>
    <!-- 订单信息部分 -->
    <h3>订单信息：</h3>
    <div class="order-info">
      <p>
        {{ orders.business.businessName }}
        <i class="fa fa-caret-down" @click="detailetShow"></i>
      </p>
      <p>&#165;{{ orders.orderTotal }}</p>
    </div>
    <!-- 订单明细部分 -->
    <ul class="order-detailet" v-show="isShowDetailet">
      <li v-for="item in orders.list">
        <p>
          {{ item.food.foodName }} x {{ item.quantity }} x
          {{ grade }}
        </p>
        <p>
          &#165;{{ item.food.foodPrice * item.quantity * (1 - grade * 0.1) }}
        </p>
      </li>
      <li>
        <p>配送费</p>
        <p>&#165;{{ orders.business.deliveryPrice }}</p>
      </li>
    </ul>
    <!-- 支付方式部分 -->
    <ul class="payment-type">
      <input type="radio" style="width: 6vw; height: 4.5vw" name="支付方式">钱包余额支付</input>
    </ul>
    <input type="checkbox" class="usePoint" style="width: 6vw; height: 4.5vw;" v-model="usePoint" value="1">使用积分抵扣金额</input>
    <div>当前积分可抵扣: {{point/100}}元</div>
    <div class="payment-button">
      <button @click="payment">确认支付</button>
    </div>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Index from "../../../../../4.SpringBoot项目/前端部分/elmclient/src/views/Index.vue";
import Footer from "../components/Footer.vue";
export default {
  name: "Payment",
  data() {
    return {
      orderId: this.$route.query.orderId,
      orders: {
        business: {},
      },
      grade: 0,
      isShowDetailet: false,
      user: {},
      usePoint: 0,
      point: 0,
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    this.$axios
      .post(
        "OrdersController/getOrdersById",
        this.$qs.stringify({
          orderId: this.orderId,
        })
      )
      .then((response) => {
        this.orders = response.data;
      })
      .catch((error) => {
        console.error(error);
      });

    //查询当前会员等级
    this.user = this.$getSessionStorage("user");
    this.$axios
      .post(
        "MembershipController/getMembershipById",
        this.$qs.stringify(this.user)
      )
      .then((response) => {
        this.grade = response.data;
      })
      .catch((error) => {
        this.grade = 0;
        console.error(error);
      });

      this.$axios
      .post("IntegralController/getPointsById", this.$qs.stringify(this.user))
      .then((response) => {
        this.point = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  mounted() {
    //这里的代码是实现：一旦路由到在线支付组件，就不能回到订单确认组件。
    //先将当前url添加到history对象中
    history.pushState(null, null, document.URL);
    //popstate事件能够监听history对象的变化
    window.onpopstate = () => {
      this.$router.push({ path: "/index" });
    };
  },
  destroyed() {
    window.onpopstate = null;
  },
  methods: {
    detailetShow() {
      this.isShowDetailet = !this.isShowDetailet;
    },
    payment() {
      // this.$axios
      //     .post(
      //       "OrdersController/payOrders",
      //       this.$qs.stringify({
      //         orderId: this.orderId,
      //       })
      //     )
      //     .then((response) => {
      //       if (response.data > 0) {
      //         alert("支付订单成功！");
      //         this.$router.go(-1);
      //       } else {
      //         alert("支付订单失败！");
      //       }
      //     })
      //     .catch((error) => {
      //       console.error(error);
      //     });
      if (this.usePoint == 0) {
        this.$axios
          .post(
            "OrdersController/payOrders",
            this.$qs.stringify({
              orderId: this.orderId,
            })
          )
          .then((response) => {
            if (response.data > 0) {
              alert("支付订单成功！");
              this.$router.go(-1);
            } else {
              alert("支付订单失败！");
            }
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        this.$axios
          .post(
            "OrdersController/usePointPayOrders",
            this.$qs.stringify({
              orderId: this.orderId,
            })
          )
          .then((response) => {
            if (response.data > 0) {
              alert("支付订单成功！");
              this.$router.go(-1);
            } else {
              alert("支付订单失败！");
            }
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
  },
  components: {
    Footer,
  },

  Index,
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
/****************** 订单信息部分 ******************/
.wrapper h3 {
  margin-top: 12vw;
  box-sizing: border-box;
  padding: 4vw 4vw 0;
  font-size: 4vw;
  font-weight: 300;
  color: #999;
}
.wrapper .order-info {
  box-sizing: border-box;
  padding: 4vw;
  font-size: 4vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .order-info p:last-child {
  color: orangered;
}
/****************** 订单明细部分 ******************/
.wrapper .order-detailet {
  width: 100%;
}
.wrapper .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 4vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .order-detailet li p {
  font-size: 3vw;
  color: #666;
}
/****************** 支付方式部分 ******************/
.wrapper .payment-type {
  width: 100%;
}
.wrapper .payment-type li {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper .payment-type li img {
  width: 33vw;
  height: 8.9vw;
}
.wrapper .payment-type li .fa-check-circle {
  font-size: 5vw;
  color: #38ca73;
}
.wrapper .usePoint {
  margin-top: 5vw;
}
.wrapper .payment-button {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw;
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