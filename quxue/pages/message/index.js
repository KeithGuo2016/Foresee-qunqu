// pages/message/index.js
const until = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page: 1,
    pageSize: 15,
    pages: null,
    news: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

   
    let data = {
      userid: app.globalData.userInfo.id,
      page:this.data.page,
      pageSize:this.data.pageSize
    }
    until.requestGet('/news/selectMyNews', data, res => {
      console.log(res.data.data);
      this.setData({
        news: res.data.data.list,
        pages: res.data.data.pages,
        page: res.data.data.pageNum

      })
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    var that = this;
    if (that.data.page >= that.data.pages) {
      return;
    }
    let data = {
      userid: app.globalData.userInfo.id,
      page: that.data.page+1,
      pageSize: that.data.pageSize
    }
    until.requestGet('/news/selectMyNews', data, res => {
      console.log(res.data.data)

      let cont = that.data.news;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      that.setData({
        news: cont,
        pages: res.data.data.pages,
        page: res.data.data.pageNum
      })

    })

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})