// pages/search/index.js
const until = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    host: until.host,
    pages:1,
    page:1,
    seachId:'',
    nodata:false,
    type:0,
    pageSize:10,
    typeName:'',
    typeid:'',

  },
  getVal: function(e){
    this.setData({
      searchDesc: e.detail.value
    })
   
  },
  search: function(e){
    let id='';
    if (e){
      if (e.currentTarget.dataset.id){
        id = e.currentTarget.dataset.id;
      }
      this.setData({
        seachId: e.currentTarget.dataset.id,
      })
   }
    let communityId = "";
    if (app.globalData.userInfo!=null){
      communityId =app.globalData.userInfo.communityid;
    }
    let data = {
      searchDesc: this.data.searchDesc || '',
      tagId: id,
      communityId: communityId,
      type: this.data.type,
      page: 1,
      pageSize: this.data.pageSize
    }
    
    until.requestGet('/search/search',data,res=>{
     
      this.setData({
        listArr: res.data.data.list,
        pages: res.data.data.pages,
        page: res.data.data.pageNum
      })
      
    })
  },
  shequnDetail: function (e) {
    console.log(e)
    wx.navigateTo({
      url: `../communityDetail/index?id=${e.currentTarget.dataset.id}`,
    })
  },
  shekanDetail(e) {
    wx.navigateTo({
      url: `../journalDetail/index?id=${e.currentTarget.dataset.id}`,
    })
  },
  collDetail: function (e) {
    wx.navigateTo({
      url: '../collectDetail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  artDetail:function(e){
    wx.navigateTo({
      url: '../detail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    until.checkAuth();
    this.setData({
      type: options.type,
      typeName: options.typeNmae,
      typeid: options.typeid,
    })
    let userid="";
    if (app.globalData.userInfo != null){
      userid: app.globalData.userInfo.id;
    }
    let data = {
      userid: userid,
      type: this.data.type
    }
    until.requestGet('/tag/searchTagAndLikeTag',data,res=>{
      console.log(res)
      this.setData({
        tagList: res.data.data
      })
    })
    this.search();
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
    
    let communityId = "";
    if (app.globalData.userInfo != null) {
      communityId = app.globalData.userInfo.communityid;
    }
    let data = {
      searchDesc: this.data.searchDesc || '',
      tagId: this.data.seachId,
      communityId: communityId,
      type: this.data.type,
      page: this.data.page+1,
      pageSize: this.data.pageSize
    }
    var that = this;
    if (that.data.page >= that.data.pages) {
      that.setData({
        nodata:true
      })
      return;
    }
      until.requestGet('/search/search', data, res => {
      let cont = that.data.listArr;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      that.setData({
        listArr: cont,
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