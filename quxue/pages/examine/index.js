// pages/focus/index.js
const until = require('../../utils/util.js')
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    host: until.host,
    tabNav: ['审核中', '已结束', '已取消'],
    curIndex: 0,
    delBtnWidth: 185,
    productList:'',
    applyList:{},
    page:1,
    pageSize:10,
    pages:1,
    nodata:false
  },
  tabSwitch: function (e) {
    this.setData({
      curIndex: e.target.dataset.id
    })
    if (e.target.dataset.id == 0) {
      this.setData({
        productList: this.data.articleList
      })
    }
    if (e.target.dataset.id == 1){
      this.setData({
        productList: this.data.userList
      })
    }
    if (e.target.dataset.id == 2) {
      this.setData({
        productList: this.data.shequnList
      })
    }
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
    productList.map(item=>{item.xmove = 0})
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
    console.log('startX:'+this.startX)
  },

  /**
   * 处理touchend事件
   */
  handleTouchEnd(e) {
    if (e.changedTouches[0].pageX < this.startX && e.changedTouches[0].pageX - this.startX <= -30) {
      this.showDeleteButton(e)
    }else {
      this.hideDeleteButton(e)
    }
  },
  canArticleFocus: function (e) {
    var that = this
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      articleId: e.currentTarget.dataset.id,
      followNum: 1,
      userid: userid
    };
    wx.showModal({
      title: '提示',
      content: '确定要取消关注吗?',
      success: res => {
        if (res.confirm) {
          until.requestPost('/articlesFollow/cancelFollow', data, res => {
            if (res.data.msg == 'OK') {
              this.data.articleList.splice(e.currentTarget.dataset.index, 1)
              wx.showToast({
                title: '已取消'
              })
              this.setData({
                productList: this.data.articleList
              })
            }
          })
        }
      }
    })
  },
  canAuthFocus: function (e) {
    var that = this
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      authorId: e.currentTarget.dataset.id,
      userid: userid
    };
    wx.showModal({
      title: '提示',
      content: '确定要取消关注吗?',
      success: res => {
        if (res.confirm) {
          until.requestPost('/authorFollow/cancelFollow', data, res => {
            if (res.data.msg == 'OK') {
              this.data.userList.splice(e.currentTarget.dataset.index, 1)
              wx.showToast({
                title: '已取消'
              })
              this.setData({
                productList: this.data.userList
              })
            }
          })
        }
      }
    })
  },
  canFocus: function (e) {
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      communitysId: e.currentTarget.dataset.id,
      userid: userid
    };
    wx.showModal({
      title: '提示',
      content: '确定要取消关注吗?',
      success: res => {
        if (res.confirm) {
          until.requestPost('/communitysFollow/cancelFollow', data, res => {
            if (res.data.msg == 'OK') {
              this.data.shequnList.splice(e.currentTarget.dataset.index, 1)
              wx.showToast({
                title: '已取消'
              })
              this.setData({
                productList: this.data.shequnList
              })
            }
          })
        }
      }
    })
  },
  //进入文章详情
  goArticleDetail:function(e){
    
    wx.navigateTo({
      url: `../detail/index?id=${e.currentTarget.dataset.id}`,
    })
  },
  // 关注的文章
  focusArticle: function(){
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      userId: userid,
      pageSize: 10,
      page: 1
    }
    until.requestGet('/articlesFollow/meFollow', data, res => {
      this.setData({
        articleList: res.data.data.list,
        productList: res.data.data.list
      })
    })
  },
  //进入作者详情
  goAuthorDetail: function (e) {
   
    wx.navigateTo({
      url: '../memberDetail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  // 关注的作者
  focusAuthor: function () {
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      userId: userid,
      pageSize: 10,
      page: 1
    }
    until.loginPost('/authorFollow/meFollow', data, res => {
      console.log(res.data)
      this.setData({
        userList: res.data.data.list
      })
    })
  },
  
  //进入社群详情
  goCommunityDetail: function(e) {
    
    wx.navigateTo({
      url: '../communityDetail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  // 关注的社群
  focusCommunity: function () {
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      userId: userid,
      pageSize: 10,
      page: 1
    }
    until.loginPost('/communitysFollow/meFollow', data, res => {
      console.log(res.data)
      this.setData({
        shequnList: res.data.data.list
      })
    })
  },
  goApplyDetail:function(e){
console.log(e)
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //this.focusArticle();
   // this.focusAuthor();
    //this.focusCommunity();
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      userid: userid,
      pageSize: this.data.pageSize,
      page: this.data.page
    }
    until.requestGet('/apply/selectMeApplyAll', data, res => {
      this.setData({
        applyList: res.data.data.list,
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
      this.setData({
        nodata:true,
      })
      return;
    }
    let communityId = "";
    
    let datas = {
      userid: app.globalData.userInfo.id,
      page: this.data.page + 1,
      pageSize: this.data.pageSize
    };
    until.requestGet('/apply/selectMeApplyAll', datas, res => {
      console.log(res.data.data)

      let cont = that.data.applyList;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      that.setData({
        applyList: cont,
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