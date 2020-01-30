// pages/memberDetail/index.js
const until = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    host: until.host,
    openid: "",
    follText: '关注',
    page: 1,
    pageSize: 5,
    pages: null,
  },
  goCommunityDetail: function (e) {
    wx.navigateTo({
      url: `../communityDetail/index?id=${e.currentTarget.dataset.id}`
    })
  },
  goDetail: function(e){
    wx.navigateTo({
      url: `../detail/index?id=${e.currentTarget.dataset.id}`
    })
  },
  //关注
  followAuthor: function (e) {
    let userid="";
    if (app.globalData.userInfo != null){
      userid = app.globalData.userInfo.id;
    }
    let data = {
      authorId: this.data.info.id,
      userid: userid,
    }
    if (this.data.follText == '关注'){
      until.requestPost('/authorFollow/insert', data, res => {
        console.log(res.data)
        if (res.data.msg == 'OK') {
          this.setData({
            follText: '已关注'
          })
        }
      })
    }else{
      wx.showModal({
        title: '提示',
        content: '确定要取消关注吗?',
        success: res => {
          if (res.confirm) {
            until.requestPost('/authorFollow/cancelFollow', data, res => {
              console.log(res.data)
              if (res.data.msg == 'OK') {
                this.setData({
                  follText: '关注'
                })
              }
            })
          }
        }
      })
    }
  },
  onLoad: function (options) {
    
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    this.setData({
      openid: options.id
    });
    let data = {
      openid: options.id,
      page: this.data.page,
      pageSize: this.data.pageSize,
      userid: userid
    }
    until.requestGet('/wxuser/userPageInfo', data, res=>{
      console.log(res)
      let follText="关注";
      if (res.data.data.uid>0){
        follText = "已关注";
      }
      this.setData({
        info: res.data.data,
        actions: res.data.data.list.list,
        follText:follText,
        pages: res.data.data.list.pages,
        page: res.data.data.list.pageNum
      })
    })
  },
  goBack: function (e) {
    let pages = getCurrentPages();
    if (pages.length > 1) {
      wx.navigateBack();
    } else {
      wx.switchTab({
        url: '../community/index',
      })
    }

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
    var that = this;
    if (that.data.page >= that.data.pages) {
      return;
    }
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      openid: this.data.openid,
      page: this.data.page + 1,
      pageSize: this.data.pageSize,
      userid: userid
    }
    until.requestGet('/wxuser/userPageInfo', data, res => {
      console.log(res)
      let cont = that.data.actions;
      for (var i = 0; i < res.data.data.list.list.length; i++) {
        cont.push(res.data.data.list.list[i])
      }

      this.setData({
        actions:cont,
        pages: res.data.data.list.pages,
        page: res.data.data.list.pageNum
      })
    })
  },
})