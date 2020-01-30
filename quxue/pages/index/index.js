//index.js
//获取应用实例
const until = require('../../utils/util.js');
const app = getApp();

Page({
  data: {
    isShow: false,
    code: '',
    host: until.host,
    userInfo: {},
    hasUserInfo: false,
    curIndex: '',
    displayDay:until.curDay().displayDay,
    dateArr: until.curDay().dayAry,
    recomList: [],
    isZan:false,
    articleZan:0,
    
  },
  goSearch: function(){
    app.goSearch()
  },
  goDetail: function(e){
    wx.navigateTo({
      url: '../detail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  getDay: function(e){
    this.setData({
      curIndex: e.target.dataset.index
    })
  },
  //滑动切换
  swiperTab: function (e) {
    this.setData({
      curIndex: e.detail.current
    });
    if(!this.data.recomList[this.data.curIndex]){
      this.getRecomList(this.data.dateArr[e.detail.current]);
    }
  },
  getRecomList: function (date) {
    until.requestGet('/articles/index', { "recommendDate": date }, res => {
      this.data.recomList[this.data.curIndex] = res.data.data;
      this.setData({
        recomList: this.data.recomList
      })
    })
  },
  //进入更多文章列表
  goMoreArticle:function(){
    wx.navigateTo({
      url: `../list/index`
    })
  },
  goCommunityDetail:function(e){
    wx.navigateTo({
      url: `../communityDetail/index?id=${e.currentTarget.dataset.id}`
    })
  },
  onLoad: function () {
    const startIndex = 6;
    this.setData({
      curIndex: startIndex
    });
    if(!this.data.recomList[startIndex]){
      this.getRecomList(this.data.dateArr[startIndex]);
    }
  }
})
