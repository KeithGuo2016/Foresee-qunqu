// pages/community/index.wxml.js
const until = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  detail:function(e){
    if(e.currentTarget.dataset.type == 1){
      wx.navigateTo({
        url: `../communityDetail/index?id=${e.currentTarget.dataset.id}`
      })
    }
    if (e.currentTarget.dataset.type == 2) {
      wx.navigateTo({
        url: `../detail/index?id=${e.currentTarget.dataset.id}`
      })
    }
    if (e.currentTarget.dataset.type == 3) {
      wx.navigateTo({
        url: `../journalDetail/index?id=${e.currentTarget.dataset.id}`
      })
    }
  },
  goSearch: function (e) {
    let typeNmae='';
    if (e.currentTarget.dataset.type){
      typeNmae = e.currentTarget.dataset.type;
    }
    wx.navigateTo({
      url: '../commSeach/index?type=' + this.data.type + '&typeNmae=' + typeNmae + '&typeid=' + e.currentTarget.dataset.id,
    })
   // app.goSearch()
  },
  swiperTab: function(e){
    if (e.detail.current == 1){
      this.getShekan();
    } 
    if (e.detail.current == 0) {
      this.getShequn();
    }
  },
  getShequn: function(){
    this.setData({
      curV: 1,
      curIndex: 0,
      defV: '社群',
      type:0
    })
   // wx.setStorageSync('type',0)
    wx.setNavigationBarTitle({
      title: "社群"
    })
  },
  getShekan: function () {
    this.setData({
      curV: 0,
      curIndex: 1,
      defV: '社刊',
      type:3

    })
   // wx.setStorageSync('type', 3)
    until.requestGet('/magazine/selectList',{page: 1,pageSize: 10}, res =>{
      console.log(res.data)
      this.setData({
        shekanList: res.data.data.list,
        pages: res.data.data.pages,
        page: res.data.data.pageNum
      })
    })
    wx.setNavigationBarTitle({
      title: "社刊"
    })
  },
  shequnDetail: function(e){
   
    wx.navigateTo({
      url: `../communityDetail/index?id=${e.currentTarget.dataset.id}`,
    })
  },
  shekanDetail(e){
   
    wx.navigateTo({
      url: `../journalDetail/index?id=${e.currentTarget.dataset.id}`,
    })
  },
  
  data: {
    curV: true,
    curIndex: 0,
    defV: '社群',
    bannerList: '',
    host: until.host,
    indicatorDots: true,
    indicatorActiveColor: '#38bab8',
    indicatorColor: '#38bab8',
    autoplay: true,
    interval: 3000,
    duration: 1000,
    type:0,
    pages:1,
    page:1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setStorageSync('type', 0)

    //轮播图
    until.requestGet('/carousel/list',{}, res =>{
      this.setData({
        bannerList: res.data.data
      })
    })
    
    //社群类别
    until.requestGet('/community/selectList',{}, res =>{
      this.setData({
        shequnList: res.data.data.list,
        atherShequnList: res.data.data.listAther
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
    console.log("aaa")
    if (this.data.curV==1){
      return;
    }
    var that = this;
    if (that.data.page >= that.data.pages) {
      return;
    }
    
    until.requestGet('/magazine/selectList', { page: that.data.page + 1, pageSize: 10 }, res => {
      let cont = that.data.shekanList;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      that.setData({
        shekanList: cont,
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