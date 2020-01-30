// pages/addArticle/index.js
const until = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page: 1,
    pageSize: 10,
    pages: 1,
    list: [],
    id: "",
    addId: "",
    delId: "",
    addNUm: 0,
    index: "",
  },
  detail:function(e){
    wx.navigateTo({
      url: '../detail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id
    })
   
    let data = {
      gatherId: options.id,
      page: this.data.page,
      pageSize: this.data.pageSize
    }
    until.requestGet('/articles/selectGatherDetail', data, res => {
      console.log(res)
      this.setData({
        list: res.data.data.list
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
      return;
    }

    let data = {
      gatherId: this.data.id,
      page: this.data.page + 1,
      pageSize: this.data.pageSize
    }
    until.requestGet('/articles/selectGatherDetail', data, res => {
      let cont = that.data.list;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }


      that.setData({
        list: cont,
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