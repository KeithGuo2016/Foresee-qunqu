// pages/deliveryList/index.js
const until = require('../../utils/util.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page:1,
    pages:null,
    pageSize:10,
    deliveryList:{},
    id:'',
    userSeeRange: 0,
    createUserid: "",

  },
  detail: function (e) {
    if (+this.data.userSeeRange){
      if (app.globalData.userInfo){
        if (this.data.createUserid != app.globalData.userInfo.id){
          wx.showToast({
            icon: 'none',
            title: '投稿内容仅征稿方可见',
            duration: 2000,
          })
          return;
        }
      }else{
        wx.showToast({
          icon: 'none',
          title: '投稿内容仅征稿方可见',
          duration: 2000,
        })
        return;
      }
    }
   
    wx.navigateTo({
      url: '../deliveryDetail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    this.setData({
      id: options.id,
      userSeeRange: options.userSeeRange,
      createUserid: options.createUserid,
    })
    let data = {
      id: options.id,
      page: this.data.page,
      pageSize: this.data.pageSize
    };
    until.requestGet('/contribute/selectDeliveryBycontributeId', data, res => {
      this.setData({
        deliveryList: res.data.data.list,
        page: res.data.data.pageNum,
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
      return;
    }

    let datas = {
      id: this.data.id,
      page: this.data.page + 1,
      pageSize: this.data.pageSize
    };

    until.requestGet('/contribute/selectDeliveryBycontributeId', datas, res => {
      let cont = that.data.deliveryList;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      that.setData({
        deliveryList: cont,
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