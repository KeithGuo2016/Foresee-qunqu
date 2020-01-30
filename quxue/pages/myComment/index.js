// pages/myComment/index.js
const until = require('../../utils/util.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page:1,
    pageSize:10,
    commentList:{},
    nodata:false,
    pages:1,
  },
 
  goDetail: function (e) {
    wx.navigateTo({
      url: '../detail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    let data={
      userid: app.globalData.userInfo.id,
      page:this.data.page,
      pageSize:this.data.pageSize,
    }
    until.requestGet('/comment/selectMyComment', data, res => {
      console.log(res)
      this.setData({
        commentList: res.data.data.list,
        page: this.data.page,
        pages: res.data.data.pages,
      })
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

    var that = this;
    if (that.data.page >= that.data.pages) {
      this.setData({
        nodata: true
      })
      return;
    }
    let data = {
      userid: app.globalData.userInfo.id,
      page: this.data.page + 1,
      pageSize: this.data.pageSize
    };
    until.requestGet('/comment/selectMyComment', data, res => {
      let cont = that.data.commentList;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      this.setData({
        commentList: cont,
        page: this.data.page + 1,
        pages: res.data.data.pages,
      })

    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})