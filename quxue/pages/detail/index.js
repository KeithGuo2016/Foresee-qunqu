// pages/detail/index.js
const until = require('../../utils/util.js');
const app = getApp();
var WxParse = require('../wxParse/wxParse.js');
Page({
  //关注
  followAuthor: function () {
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id
    }
    let data = {
      authorId: this.data.detail.userid,
      userid: userid,
    }
    until.requestPost('/authorFollow/insert', data, res => {
      console.log(res.data)
      if (res.data.msg == 'OK') {
        var isFollowAuthor = !this.data.isFollowAuthor;
        this.setData({
          isFollowAuthor: isFollowAuthor
        })
      }
    })

  },
  //取消关注
  notFollowAuthor: function () {
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id
    }
    let data = {
      authorId: this.data.detail.userid,
      userid: userid,
    }
    until.requestPost('/authorFollow/cancelFollow', data, res => {
      console.log(res.data)
      if (res.data.msg == 'OK') {
        var isFollowAuthor = !this.data.isFollowAuthor;
        this.setData({
          isFollowAuthor: isFollowAuthor
        })
      }
    })

  },
  //进入社群详情
  goCommunity: function (e) {

    wx.navigateTo({
      url: `../communityDetail/index?id=${e.currentTarget.dataset.id}`
    })
  },
  //进入作者详情
  goAuthorDetail: function (e) {

    wx.navigateTo({
      url: '../memberDetail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  //打开评论编辑页
  goComment: function (e) {
    this.setData({
      sessionId: e.currentTarget.dataset.id,
      pid: e.currentTarget.dataset.pid,
      comment: true
    })
  },
  //取消评论
  cancelComment: function () {
    this.setData({
      tocontent: "",
      comment: false
    })
  },
  //发布评论
  doComment: function () {
    //获取文章id：
    let articleid = this.data.detail.id;
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id
    }
    if (!this.data.tocontent.length > 0) {
      wx.showToast({
        title: '请输入评论内容',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    //sessionId>0 表示跟帖回复，否则是评论文章
    if (this.data.sessionId > 0) {
      let data = {
        articlesId: articleid,
        content: this.data.tocontent,
        pid: this.data.pid,
        toUserId: this.data.sessionId,
        userid: userid,
      }
      until.requestPost('/comment/insert', data, res => {
        if (res.data.msg == 'OK') {
          this.setData({
            comment: false
          })
          wx.showToast({
            title: '评论成功',
            icon: 'none',
            duration: 2000
          })
        }
      })
    } else {
      var that = this;
      let data = {
        articlesId: articleid,
        content: this.data.tocontent,
        userid: userid,
      }
      until.requestPost('/comment/insert', data, res => {
        if (res.data.msg == 'OK') {
          let comment = that.data.commentList;
          comment.unshift(res.data.data)
          this.setData({
            commentList: comment,
            comment: false
          })
          wx.showToast({
            title: '评论成功',
            icon: 'none',
            duration: 2000
          })
        }
      })
    }
    //刷新评论区
    this.getcomment();
  },
  //获取评论内容
  getContent: function (res) {
    this.setData({
      tocontent: res.detail.value
    })
  },
  //进入评论详情
  moreData: function (e) {
    wx.navigateTo({
      url: '../commentDetail/index?id=' + e.currentTarget.dataset.id + '&type=' + e.currentTarget.dataset.type + "&articleId=" + this.data.detail.id,
    })
  },
  //关注
  coll() {
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id
    }
    let data = {
      articleId: this.data.detail.id,
      userid: userid,
      followNum: this.data.detail.followCount,
    }
    if (!this.data.isColl) {
      until.requestPost('/articlesFollow/insert', data, res => {

        if (res.data.msg == 'OK') {
          this.setData({
            isColl: !this.data.isColl,
            followCount: this.data.followCount + 1,
          })
        }
      })
    } else {
      until.requestPost('/articlesFollow/cancelFollow', data, res => {

        if (res.data.msg == 'OK') {
          this.setData({
            isColl: !this.data.isColl,
            followCount: this.data.followCount - 1,
          })
        }
      })
    }


  },
  zan() {
    this.setData({
      isZan: !this.data.isZan
    })
    let type = '1';
    if (!this.data.isZan) {
      type = "0";
    }
    let data = {
      id: this.data.articlesId,
      userid: app.globalData.userInfo.id,
      type: type
    }
    until.requestGet('/articles/articleZan', data, res => {
      this.setData({
        zanVal: res.data.data,
      })
    })
  },

  data: {
    isLogin: false,
    comment: false,
    isColl: false,
    isZan: false,
    zanVal: 0,
    detail: null,
    host: until.host,
    pageSize: 10,
    page: 1,
    pageTotal: 0,
    pages: 1,
    isFollowAuthor: null,
    sessionId: 0,
    pid: "",
    tocontent: "",
    commentList: null,
    followCount: 0,
    commentCount: 0,
  },
  getcomment: function () {
    let dataComment = {
      articlesId: this.data.articlesId,
      page: this.data.page,
      pageSize: this.data.pageSize
    }
    until.requestGet('/comment/selectComment', dataComment, res => {
      this.setData({
        commentList: res.data.data.list,
        commentCount: res.data.data.list.length,
        pages: res.data.data.pages,
        pageTotal: res.data.data.total

      })
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    until.checkAuth();
    this.setData({
      articlesId: options.id,
    })
    var that = this;
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }

    let data = {
      id: options.id,
      userid: userid,
    }
    until.requestGet('/articles/selectVoById', data, res => {
      let isZan = false;
      if (res.data.data.isZan) {
        isZan = true
      }
      this.setData({
        detail: res.data.data,
        isFollowAuthor: res.data.data.isFollowAuthor,
        isColl: res.data.data.isFollow,
        followCount: res.data.data.followCount,
        zanVal: res.data.data.articleZan,
        isZan: isZan,
      })
      WxParse.wxParse('content', 'html', res.data.data.articleContent, that, 5);
    })
    that.getcomment();
  },


  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    if (this.data.page >= this.data.pages) {
      return;
    }
    let dataComment = {
      articlesId: this.data.articlesId,
      page: this.data.page + 1,
      pageSize: this.data.pageSize
    }
    var that = this;
    until.requestGet('/comment/selectComment', dataComment, res => {

      let cont = that.data.commentList;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      that.setData({
        commentList: cont,
        pages: res.data.data.pages,
        page: res.data.data.pageNum
      })
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function (res) {
    var shareObj = {
      title: "【群趣创作】" + this.data.detail.articleTitle,
      path: '/pages/detail/index?id=' + this.data.detail.id,
      imageUrl: this.data.detail.articleIcon,
      complete: function () {
        return;
      }
    };
    // 来自页面内的按钮的转发
    if (res.from == 'button') {

    }
    // 返回shareObj
    return shareObj;
  }
})