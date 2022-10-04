<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>发布评论</p>
    </header>
    <!-- 写评论部分 -->
    <textarea v-model="text" name="" id="" cols="50" rows="10"></textarea>
    <div class="button-login">
      <button @click="postComment">发布</button>
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
      text: "",
      businessId: this.$route.query.businessId,
    };
  },
  methods: {
    postComment() {
      this.user = this.$getSessionStorage("user");
      this.$axios
        .post(
          "CommentController/saveComment",
          this.$qs.stringify({
            user: this.user,
            businessId: this.businessId,
            text: this.text,
          })
        )
        .then((response) => {
          if (response.data > 0) {
            alert("发布评论成功！");
            this.$router.go(-1);
          } else {
            alert("发布评论失败！");
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
/* 写评论部分 */
.wrapper textarea {
  margin-top: 14vw;
  padding: 0.5rem 0.75rem;
  background-color: #ffffff;
  margin-bottom: 0.5rem;
  border-radius: 0.5rem;
  width: 21rem;
  margin-left: 3.5vw;
  box-sizing: border-box;
  --mainColor: #02b6fd;
  --lightColor: #e6f8ff;
  --lightBgColor: rgba(2, 182, 253, 0.08);
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