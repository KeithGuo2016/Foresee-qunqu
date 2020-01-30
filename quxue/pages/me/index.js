// pages/me/index.js
const until = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ewm: false,
    applyPub: false,
    showCode: true,
    isAddCommunity:true,
    time: 60,
  },
  showEwm: function(){
    this.setData({
      ewm: !this.data.ewm
    })
  },
  goWriting: function(){
    if (app.globalData.userInfo){
      if (app.globalData.userInfo.isAddCommunityAplly) {
        wx.showToast({
          icon: 'none',
          title: '您申请加入的社群正在审核中，审核通过才能发布文章',
          duration: 2000,
        })
       return;
      }
    }
    
    wx.navigateTo({
      url: '../writing/index',
    })
  },
  goCollect: function () {
    wx.navigateTo({
      url: '../pub/index',
    })
  },
  goTmp: function () {
    wx.navigateTo({
      url: '../temp/index',
    })
  },
  shequnDetail(e){
    if (app.globalData.userInfo.isAddCommunityAplly){
      wx.showToast({
        icon: 'none',
        title: '您申请创建的社群正在审核中，请耐心等待',
        duration:2000,
      })
      return;
    }
    if (app.globalData.userInfo.communityid<=0){
      wx.showModal({
        title: '提示',
        content: '您没加入社群，需要选择社群吗？',
        confirmText: '选择社群',
        success(res) {
          if (res.confirm) {
            wx.reLaunch({
              url: '../community/index'
            })

          } else if (res.cancel) {
            return;
          }
        }
      })
      return;
    }
    wx.navigateTo({
      url: `../communityDetail/index?id=${e.currentTarget.dataset.id}`,
    })
  },
  myArt:function(){
    wx.navigateTo({
      url: '../memberDetail/index?id=' + app.globalData.userInfo.openId,
    })
  },
  myApply:function(){
    wx.navigateTo({
      url: '../examine/index?id=' + app.globalData.userInfo.openId,
    })
  },
  myFocus(){
    wx.navigateTo({
      url: '../focus/index',
    })
  },
  myComment(){
    wx.navigateTo({
      url: '../myComment/index',
    })
  },
  needPub(){
    this.setData({
      applyPub: true
    })
  },
  goSet(){
    wx.navigateTo({
      url: '../setting/index',
    })
  },
  goMessage(){
    wx.navigateTo({
      url: '../message/index',
    })
  },
  myFans(){
    wx.navigateTo({
      url: '../fans/index',
    })
  },
  myGather() {
    wx.navigateTo({
      url: '../gather/index',
    })
  },
  creatComm(){
    if (app.globalData.userInfo.isAddCommunityAplly) {
      wx.showToast({
        icon: 'none',
        title: '您申请创建的社群正在审核中，请耐心等待',
        duration: 2000,
      })
      return;
    }
    wx.navigateTo({
      url: '../creatCommunity/index',
    })
  },
  canApply() {
    this.setData({
      applyPub: false
    })
  },
  getName: function (e) {
    this.setData({
      userName: e.detail.value
    })
  },
  getApply: function (e) {
    console.log(e)
    this.setData({
      applyDesc: e.detail.value
    })
  },
  checkPhone: function (e) {
    if ((/^1[3-9]\d{9}$/).test(e.detail.value)) this.setData({
      phoneVerify: true,
      phone: e.detail.value
    })
    else this.setData({
      phoneVerify: false
    })
  },
  getCode: function () {
    let inter;
    let data = {
      userid: app.globalData.userInfo.id,
      phone: this.data.phone
    };
    until.loginPost('/apply/getCodeu', data, res => {
      console.log(res)
    })
    if (this.data.phoneVerify) {
      this.setData({
        showCode: false
      })
      inter = setInterval(() => {
        this.data.time--;
        if (this.data.time >= 0) {
          this.setData({
            time: this.data.time--
          })
        }
        else {
          clearInterval(inter)
          this.setData({
            time: 60,
            showCode: true
          })
        }
      }, 1000)

    }
  },
  codeNum: function (e) {
    if ((/^\d{6}$/).test(e.detail.value)) this.setData({
      code: e.detail.value
    })
  },
  goApply: function () {
    let data = {
      userid: app.globalData.userInfo.id,
      userName: this.data.userName,
      phone: this.data.phone,
      code: this.data.code,
      applyDesc: this.data.applyDesc
    }
    if (!this.data.userName) {
      wx.showToast({
        icon: 'none',
        title: '请输入姓名！',
      })
    }
    else if (!this.data.phone) {
      wx.showToast({
        icon: 'none',
        title: '请输入手机号！',
      })
    }
    else if (!this.data.code) {
      wx.showToast({
        icon: 'none',
        title: '请输入验证码！',
      })
    }
    else {
      until.requestPost('/apply/contributeApply', data, res => {
        if (res.data.msg == 'OK') {
          wx.showToast({
            title: '申请已提交',
          })
        } else {
          wx.showToast({
            icon: 'none',
            title: res.data.msg,
          })
        }
        this.setData({
          applyPub: false
        })
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    until.checkAuth();
    if (app.globalData.userInfo) {
      if (app.globalData.userInfo.communityid <= 0) {
        this.setData({
          isAddCommunity: false
        })
      }
    }
    this.setData({
      userInfo: app.globalData.userInfo
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

  }
})