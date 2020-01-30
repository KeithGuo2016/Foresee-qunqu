// pages/commentDetail/index.js

const app = getApp();
const until = require('../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
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
      content: "",
      comment: false
    })
  },
  doComment:function(){
    
    let articleid = this.data.articleid;
    if (!this.data.content.length > 0) {
      wx.showToast({
        title: '请输入评论内容',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    if (this.data.sessionId > 0) {
     
      let data = {
        articlesId: this.data.articleid,
        content: this.data.content,
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
        articlesId: this.data.articleid,
        content: this.data.content,
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
  },
  //获取评论内容
  getContent: function (res) {
    this.setData({
      content: res.detail.value
    })
  },
  //展开评论条
  moreData:function(e){
    
    wx.navigateTo({
      url: '../commentDetail/index?id=' + e.currentTarget.dataset.id + '&type=' +'comment'  + "&articleId=" + this.data.id,
    })
  },
  data: {
    host: until.host,
    comment: false,
    articleid:"",
    type:"",
    page:1,
    pageSize:10,
    pages:1,
    commentList:null,
    id:null,
    //列表还是单个回复，false表示单个回复
    ismore:false,
    sessionId: 0,
    pid: "",
    content: "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    let data = {
      id: options.id,
      type: options.type,
      page:this.data.page,
      pageSize:this.data.pageSize,
    }
    until.requestGet('/comment/selectCommentByType', data, res => {
      console.log(res.data.data)
      if (options.type == 'comment'){
        this.setData({
          type: options.type,
          id: options.id,
          articleid: options.articleId,
          commentList: res.data.data,
        })
      }
      if (options.type == 'article') {
        this.setData({
          type: options.type,
          id: options.id,
          articleid: options.articleId,
          commentList: res.data.data.list,
          pages: res.data.data.pages,
          page: res.data.data.pageNum,
          ismore:true,
        })
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
    //执行分页操作
    var that = this;
    if (that.data.type =="comment"){
      return;
    }
    if (that.data.page >= that.data.pages) {
      return;
    }
    let datas = {
      id: that.data.id,
      type: options.type,
      page: that.data.page + 1,
      pageSize: that.data.pageSize
    };
    until.requestGet('/comment/selectCommentByType', data, res => {
      console.log(res.data.data)

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