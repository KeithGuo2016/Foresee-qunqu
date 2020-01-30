// pages/journalDetail/index.js
const until = require('../../utils/util.js')
const app = getApp();
var WxParse = require('../wxParse/wxParse.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    host: until.host,
    leftNav: true,
    translate: 0,
    cover: true,
    time: 3,
    index: 1,
    comment: false,
    isZan: false,
    isColl:false,
    followCount:0,
    zanVal:0,
    tocontent:'',
    commentList:[],
    pageSize: 10,
    page: 1,
    pageTotal: 0,
    pages: 1,
    articlesId:'',

  },
  coverShow(){
    this.data.time --;
    if(this.data.time <= 0){
      this.setData({
        cover: false
      })
      return;
    }
    setTimeout(()=>{this.coverShow()}, 1000)
  },
  start(e){
    this.startX = e.touches[0].pageX
  },
 
  end(e) {
    if (e.changedTouches[0].pageX < this.startX) {
      this.setData({
        leftNav: false,
        translate: '-750rpx'
      })
    }
  },
  closeCover: function (e) {
    this.setData({
      cover: false
    })
  },
  show:function(){
    this.setData({
      leftNav: false,
      translate: 0
    })
  },
  getArticle(id){
    //查询文章视图
    var that = this
    let userid="";
    if (app.globalData.userInfo!=null){
      userid = app.globalData.userInfo.id;
    }
    let data = {
      id: id,
      userid: userid,
    }
    until.requestGet('/articles/selectVoById', data, res => {
      console.log(res.data.data)
      let isZan = false;
      if (res.data.data.isZan) {
        isZan = true
      }
      this.setData({
        arcdetail: res.data.data,
        isFollowAuthor: res.data.data.isFollowAuthor,
        isColl: res.data.data.isFollow,
        followCount: res.data.data.followCount,
        zanVal: res.data.data.articleZan,
        isZan: isZan,
      })
      WxParse.wxParse('content', 'html', res.data.data.articleContent, that, 5);
    })
    this.getcomment(id);
  },
  detail(e){
    this.setData({
      index: e.currentTarget.dataset.index+1,
      translate: '-750rpx',
      articlesId: e.currentTarget.dataset.id,
      page:1,
      pages:1,
    })
    this.getArticle(e.currentTarget.dataset.id);
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
  doComment: function (e) {
    //获取文章id：
    let articleid = e.currentTarget.dataset.id;
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
    this.getcomment(articleid);
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
  coll(e) {
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id
    }
    let data = {
      articleId: e.currentTarget.dataset.id,
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
  zan(e) {
    until.checkAuth();
    this.setData({
      isZan: !this.data.isZan
    })
    let type = '1';
    if (!this.data.isZan) {
      type = "0";
    }
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id
    }
    let data = {
      id: e.currentTarget.dataset.id,
      userid: userid,
      type: type
    }
    until.requestGet('/articles/articleZan', data, res => {
      this.setData({
        zanVal: res.data.data,
      })
    })
  },
  getcomment: function (id) {
    let dataComment = {
      articlesId: id,
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
    
    this.coverShow();
    until.requestGet('/magazine/selectArticles',{id: options.id}, res=>{
      console.log(res.data)
      this.setData({
        detail: res.data.data,
        articlesId: res.data.data.list[0].articlesId,
      })
      if (res.data.data.list.length>0){
        this.getArticle(res.data.data.list[0].articlesId)
       
      }
     
    })
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
  onShareAppMessage: function () {

  }
})