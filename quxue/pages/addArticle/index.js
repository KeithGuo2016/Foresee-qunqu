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
    pages:1,
    list:[],
    id:"",
    addId:"",
    delId:"",
    addNUm:0,
    index:"",
  },
  add:function(e){
    let list=this.data.list;
   
    let isadd = list[e.currentTarget.dataset.index].isFollow;
    if (isadd){
      list[e.currentTarget.dataset.index].isFollow = 0;
     // delId = list[e.currentTarget.dataset.index].id;
    }else{
      list[e.currentTarget.dataset.index].isFollow = 1;
      //addId = list[e.currentTarget.dataset.index].id;
    }
    this.setData({
      list: list
    })
  },
  pub:function(){
    let list = this.data.list;
    let delId = "";
    let addId = "";
    let addNUm=0;
    for (var i = 0; i < list.length; i++) {
      if (list[i].isFollow == 0 && list[i].gatherId>0){
        delId = delId + list[i].id+",";
      }
      if (list[i].isFollow == 1 && list[i].gatherId == null) {
        addId = addId + list[i].id + ",";
        addNUm++
      }
    }

    delId = delId.substring(0, delId.length -1);
    addId = addId.substring(0, addId.length - 1);
    if (this.data.id ==""){
      this.setData({
        addId: addId,
       
      })
      wx.navigateBack({
        delta: 1
      })
    return;
    }
    let data={
      delId: delId,
      addId: addId,
      gatherId: this.data.id,
      userid : app.globalData.userInfo.id
    }
    until.requestGet('/articles/addArticleToGatherId', data, res => {
      this.setData({
        addNUm: addNUm
      })
      wx.navigateBack({
        delta: 1
      })
    })

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
    this.setData({
      id:options.id,
      index: options.index,
    })
    let userid = "";
    if (app.globalData.userInfo) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      userid: userid,
      gatherId: options.id,
      page: this.data.page,
      pageSize: this.data.pageSize
    }
    if (options.id == "") {
      data = {
        userid: userid,
        page: this.data.page,
        pageSize: this.data.pageSize
      }
    }
   
    until.requestGet('/articles/selectByGatherId', data, res => {
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
    if (this.data.id ==""){
      let list = this.data.list;
      let articleList = [];
      for (var i = 0; i < list.length; i++) {
        if (list[i].isFollow == 1 && list[i].gatherId == null) {
          articleList.push(list[i]);
        }
      }
      var pages = getCurrentPages();
      var currPage = pages[pages.length - 1];   //当前页面
      var prevPage = pages[pages.length - 2];  //上一个页面
      let index = this.data.index;
     let lista =  prevPage.data.list;
      lista[index].articleCount = this.data.addNUm;
      
      prevPage.setData({
        articleList: articleList,
        list: lista
      });
    }else{
      let index = this.data.index;
      var pages = getCurrentPages();
      var currPage = pages[pages.length - 1];   //当前页面
      var prevPage = pages[pages.length - 2];  //上一个页面
      let list = prevPage.data.list;
      list[index].articleCount = this.data.addNUm;
      prevPage.setData({
        list: list
      });
    }

    
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
      userid: app.globalData.userInfo.id,
      gatherId: this.data.id,
      page: this.data.page + 1,
      pageSize: this.data.pageSize
    }
    until.requestGet('/articles/selectByGatherId', data, res => {
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
  }
})