// pages/collect/index.js
const until = require('../../utils/util.js');
const app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  goSearch: function () {
    app.goSearch()
  },
  collDetail: function(e){
    wx.navigateTo({
      url: '../collectDetail/index?id='+e.currentTarget.dataset.id,
    })
  },
  data: {
    host: until.host,
    contributes: null,
    page: 1,
    pageSize: 5,
    pages:null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setStorageSync('type', 5)
    wx.showTabBar();
    let communityId = "";
    if (app.globalData.userInfo != null) {
      communityId = app.globalData.userInfo.communityid;
    }
    let data = {
      communityId: communityId,
      page:this.data.page,
      pageSize: this.data.pageSize
    };
    until.requestGet('/contribute/selectAllList', data, res => {
      console.log(res.data.data)
      this.setData({
        contributes: res.data.data.list,
        pages: res.data.data.pages,
        page: res.data.data.pageNum
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
    /*wx.showTabBar();
    let communityId = "";
    if (app.globalData.userInfo != null) {
      communityId = app.globalData.userInfo.communityid;
    }
    let data = {
      communityId: communityId,
      // communityId:"",
      page: 1,
      pageSize: this.data.pageSize
    };
    until.requestGet('/contribute/selectAllList', data, res => {
      console.log(res.data.data)
      this.setData({
        contributes: res.data.data.list,
        pages: res.data.data.pages,
        page: res.data.data.pageNum
      })

    })*/
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
    var that =this;
    if (that.data.page >= that.data.pages){
     
      return;
    }
    let communityId = "";
    if (app.globalData.userInfo != null) {
      communityId = app.globalData.userInfo.communityid;
    }
    let datas = {
      communityId: communityId,
      page: this.data.page + 1,
      pageSize: this.data.pageSize
    };
    until.requestGet('/contribute/selectAllList', datas, res => {
      console.log(res.data.data)
     
      let cont = that.data.contributes;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      that.setData({
        contributes: cont,
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