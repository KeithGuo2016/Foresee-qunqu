// pages/message/index.js
const until = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tempList:[],
    page: 1,
    pageSize: 10,
    pages: 1,

  },
  goWrite:function(e){
    wx.navigateTo({
      url: '../writing/index?id=' + e.currentTarget.dataset.id,
    })

  },
  /**
   * 显示删除按钮
   */
  showDeleteButton: function (e) {
    let productIndex = e.currentTarget.dataset.productindex
    this.setXmove(productIndex, -160)
  },


  /**
   * 隐藏删除按钮
   */
  hideDeleteButton: function (e) {
    let productIndex = e.currentTarget.dataset.productindex

    this.setXmove(productIndex, 0)
  },

  /**
   * 设置movable-view位移
   */
  setXmove: function (productIndex, xmove) {
    let productList = this.data.productList
    productList.map(item => { item.xmove = 0 })
    productList[productIndex].xmove = xmove

    this.setData({
      productList: productList
    })
  },
  handleMovableChange: function (e) {
    if (e.detail.source === 'friction') {
      if (e.detail.x < -30) {
        this.showDeleteButton(e)
      } else {
        this.hideDeleteButton(e)
      }
    } else if (e.detail.source === 'out-of-bounds' && e.detail.x === 0) {
      this.hideDeleteButton(e)
    }
  },

  /**
   * 处理touchstart事件
   */
  handleTouchStart(e) {
    this.startX = e.touches[0].pageX
    console.log('startX:' + this.startX)
  },

  /**
   * 处理touchend事件
   */
  handleTouchEnd(e) {
    if (e.changedTouches[0].pageX < this.startX && e.changedTouches[0].pageX - this.startX <= -30) {
      this.showDeleteButton(e)
    } else {
      this.hideDeleteButton(e)
    }
  },
  delTemp: function(e){
    until.requestPost('/articles/delete',{id: e.currentTarget.dataset.id},res=>{
      console.log(res)
      if(res.data.msg == "OK"){
        this.data.productList.splice(e.currentTarget.dataset.index, 1)
        wx.showToast({
          title: '已删除',
        })
        this.setData({
          productList: this.data.productList
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let data = {
      userId: app.globalData.userInfo.id,
      page: this.data.page,
      pageSize: this.data.pageSize
    };
    until.requestGet('/articles/selectTempList', data, res => {
      console.log(res.data.data)
      this.setData({
        productList: res.data.data.list,
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
      userId: app.globalData.userInfo.id,
      page: this.data.page,
      pageSize: this.data.pageSize
    };
    until.requestGet('/articles/selectTempList', data, res => {
      console.log(res.data.data)
      let cont = that.data.tempList;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      that.setData({
        tempList: cont,
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