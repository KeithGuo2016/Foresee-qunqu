const until = require('../../utils/util.js')
//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    code: '',
    userInfo: {},
    hasUserInfo: false,
    path: "",
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  onLoad: function (options) {
    this.data.rt = options.rt;
    // 登录
    wx.login({
      success: res => {
        console.log(res)
        this.data.code = res.code;
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  getUserInfo: function (e) {
    var that = this
    if (e.detail.userInfo) {
      wx.showLoading({
        title: '加载中...',
      })
      let data = {
        code: this.data.code,
        iv: e.detail.iv,
        encryptedData: e.detail.encryptedData,
        eid: app.globalData.eid
      };
      until.loginPost('/doLogin', data, res => {
        wx.hideLoading()
        let that = this;
        if (res.data.msg == 'OK') {
          wx.setStorageSync('openId', res.data.data.openId);
          app.globalData.userInfo = res.data.data;
          if (!!this.data.rt) {
            wx.redirectTo({
              url: decodeURIComponent(that.data.rt),
              //调用 switchtab
              fail: function () {
                wx.switchTab({
                  url: decodeURIComponent(that.data.rt),
                })
              }
            });
          } else {
            wx.switchTab({
              url: '../index/index',
            })
          }
        }
        else {
          wx.showToast({
            icon: 'none',
            title: res.data.msg,
          })
        }
      })

    }
    else {
      wx.switchTab({
        url: '../index/index',
      })
      wx.openSetting({

      })
    }
  }
})
