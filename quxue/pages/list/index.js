// pages/list/index.js
const until = require('../../utils/util.js');
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    recomList: {},
    page: 1,
    pageSize: 10,
    pages: null,
  },
  goDetail: function(e) {

    wx.navigateTo({
      url: '../detail/index?id=' + e.currentTarget.dataset.id,
    })
  },

  goSearch: function() {
    //app.goSearch()
    wx.navigateTo({
      url: '../artticleSeach/index',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    until.checkAuth();
    let data = {
      page: this.data.page,
      pageSize: this.data.pageSize,
    };
    //wx.setStorageSync('type', 1)
    until.requestGet('/articles/selectRecommendList', data, res => {
      this.setData({
        recomList: res.data.data.list,
        page: res.data.data.pageNum,
        pages: res.data.data.pages,
      })
      if (res.data.data.pageNum == res.data.data.pages) {
        this.setData({
          nodata: true,
        })
      }
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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    var that = this;
    if (that.data.page >= that.data.pages) {
      this.setData({
        nodata: true
      })
      return;
    }
    let data = {
      page: this.data.page + 1,
      pageSize: this.data.pageSize
    };
    until.requestGet('/articles/selectRecommendList', data, res => {
      let cont = that.data.recomList;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      this.setData({
        recomList: cont,
        page: res.data.data.pageNum,
        pages: res.data.data.pages,
      })
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})