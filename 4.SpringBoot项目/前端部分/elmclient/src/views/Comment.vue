<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>评论</p>
    </header>
    <!-- 评论列表部分 -->
    <div class="shop-detail">
      <div class="tab-comments">
        <div class="shop-comments-wrap">
          <div v-for="item in commentArr" class="item">
            <div class="user">
              <img src="../assets/头像.png" alt="头像" />
              <div class="userinfo">
                <div class="username">{{ getUsername(item.userId) }}</div>
                <div class="usermembership">
                  {{ getUserMembership(item.userId) | gradeNameFilter }}会员
                </div>
              </div>
            </div>
            <div class="comment">
              <div class="text">
                {{ item.text }}
              </div>
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
      commentArr: [],
      businessId: this.$route.query.businessId,
    };
  },
  create() {
    this.$axios
      .post(
        "CommentController/listCommentByBusinessId",
        this.$qs.stringify({
          businessId: this.businessId,
        })
      )
      .then((response) => {
        this.commentArr = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    getUsername(userId) {
      this.$axios
        .post(
          "UserController/getuserNameByuserId",
          this.$qs.stringify({
            userId,
          })
        )
        .then((response) => {
          userName = response.data;
          return userName;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getUserMembership(userId) {
      this.$axios
        .post(
          "MembershipController/getMembershipById",
          this.$qs.stringify({
            userId,
          })
        )
        .then((response) => {
          userGrade = response.data;
          return userGrade;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  filters: {
    gradeNameFilter(grade) {
      if (grade == 0) return "普通";
      else if (grade == 1) return "白银";
      else if (grade == 2) return "黄金";
      else return "钻石";
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
/* 评论列表部分 */
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
.wrapper .shop-detail .tab-comments .shop-comments-wrap .item .user {
  padding: 0.5rem 0;
  display: flex;
  align-items: center;
  line-height: 1;
}
.wrapper .shop-detail .tab-comments .shop-comments-wrap .item .user img {
  width: 1.5rem;
  height: 1.5rem;
  border-radius: 0.75rem;
}
.wrapper .shop-detail .tab-comments .shop-comments-wrap .item .user .userinfo {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  flex: 1;
}
.wrapper
  .shop-detail
  .tab-comments
  .shop-comments-wrap
  .item
  .user
  .userinfo
  .username {
  margin: 0 0.25rem;
  font-size: 0.875rem;
  font-weight: 700;
}
.wrapper
  .shop-detail
  .tab-comments
  .shop-comments-wrap
  .item
  .user
  .userinfo
  .usermembership {
  margin: 0 0.25rem;
  font-size: 0.875rem;
}
.wrapper .shop-detail .tab-comments .shop-comments-wrap .comment {
  margin-top: -0.25rem;
}
.wrapper .shop-detail .tab-comments .shop-comments-wrap .comment .text {
  line-break: anywhere;
  word-break: break-word;
}
</style>