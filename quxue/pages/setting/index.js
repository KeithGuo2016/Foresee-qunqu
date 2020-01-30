// pages/setting/index.js
const app = getApp()
const until = require('../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    headUrl: '',
    name: '',
    phone:'',
    mark: '',
    comment: false,
    content:'',
    changePhone:false,
    phoneVerify:true,
    showCode: true,
    time: 60,
  },
  chooseImg: function(){
    wx.chooseImage({
      count: 1,
      sizeType: ['compressed'],
      success: res => {
        wx.showLoading();
        wx.uploadFile({
          url: until.host + '/img/uploadIconScale',
          filePath: res.tempFiles[0].path,
          name: 'file',
          formData: {
            userId: wx.getStorageSync('openId'),
            imageWidth:600,
            imageHeight:600,
          },
          success: data => {
            
            wx.hideLoading();
            if (JSON.parse(data.data).status == 200){
              this.setData({
                headUrl: JSON.parse(data.data).data
              })
            }
            if (JSON.parse(data.data).status == 500){
              wx.showToast({
                icon:'none',
                title: JSON.parse(data.data).msg,
                duration:3000
              })
            }
          }
        })

      }
    })
  },
  getName:function(e){
    this.setData({
      name: e.detail.value
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
  codeNum: function (e) {
    if ((/^\d{6}$/).test(e.detail.value)) this.setData({
      code: e.detail.value
    })
  },
  canApply() {
    this.setData({
      changePhone: false
    })
  },
  goApply: function () {
   if (!this.data.phone) {
      wx.showToast({
        icon: 'none',
        title: '请输入手机号！',
      })
      return
    }
    if (!this.data.code) {
      wx.showToast({
        icon: 'none',
        title: '请输入验证码！',
      })
      return;
    }
    let data = {
      id: app.globalData.userInfo.id,
      openId: wx.getStorageSync('openId'),
      phone: this.data.phone,
      code: this.data.code
    }
    until.requestPost('/wxuser/updatePhone', data, res => {
      if (res.data.msg == 'OK') {
        wx.showToast({
          title: '保存成功',
        })
        app.globalData.userInfo = res.data.data
        
        this.setData({
          changePhone: false
        })
      }
    })
    
  },
  changePhone:function(){
    this.setData({
      changePhone: true,
    })
  },
  getCode: function () {
    let inter;
    let data = {
      userid: app.globalData.userInfo.id,
      phone: this.data.phone
    };
    until.loginPost('/apply/getCodeUnique', data, res => {
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
  getcomment:function(){
    let content = this.data.mark
    this.setData({
      comment: true,
      content: content,
      mark:''
    })
  },
  getContent: function (e){
    this.setData({
      mark: e.detail.value,
    })
  },
  //取消评论
  cancelComment: function () {
    let content = this.data.content
    this.setData({
      mark: content,
      comment: false
    })
  },
  doComment: function (){
    if (this.data.mark.length <= 0) {
      wx.showToast({
        title: '请编写个性签名内容',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    this.setData({
      comment: false
    })
    this.othersave();
  },

  save: function(){
    let data = {
      id: app.globalData.userInfo.id,
      openId: wx.getStorageSync('openId'),
      headUrl: this.data.headUrl,
      nickName: this.data.name,
      phone: this.data.phone,
      userDesc: this.data.mark
    }
    until.requestPost('/wxuser/update',data,res=>{
      
      if(res.data.msg == 'OK'){
        wx.showToast({
          title: '保存成功',
        })
        setTimeout(()=>{wx.navigateBack(-1)}, 1000)
        app.globalData.userInfo = res.data.data
      }
    })
  },
  othersave: function () {
    let data = {
      id: app.globalData.userInfo.id,
      openId: wx.getStorageSync('openId'),
      headUrl: this.data.headUrl,
      nickName: this.data.name,
      phone: this.data.phone,
      userDesc: this.data.mark
    }
    until.requestPost('/wxuser/update', data, res => {
      if (res.data.msg == 'OK') {
        wx.showToast({
          title: '保存成功',
        })
        app.globalData.userInfo = res.data.data
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      userInfo: app.globalData.userInfo,
      headUrl: app.globalData.userInfo.headUrl,
      name: app.globalData.userInfo.nickName,
      phone: app.globalData.userInfo.phone,
      mark: app.globalData.userInfo.userDesc
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})