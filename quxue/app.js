//app.js
const until = require('/utils/util.js');
App({
  onLaunch: function () {
    if (wx.canIUse('getUpdateManager')) {
      const updateManager = wx.getUpdateManager()
      updateManager.onCheckForUpdate(function (res) {
        // 请求完新版本信息的回调
        if (res.hasUpdate) {
          updateManager.onUpdateReady(function () {
            updateManager.applyUpdate()
          })
          updateManager.onUpdateFailed(function () {
            // 新的版本下载失败
            wx.showModal({
              title: '已经有新版本了',
              content: '新版本已经上线，请您删除当前小程序'
            })
          })
        }
      })
    }
    if (wx.getStorageSync('openId')) {
      until.requestGet('/wxuser/selectByopenId', { openid: wx.getStorageSync('openId'), eid: this.globalData.eid}, res => {
        if (res.data.msg == 'OK') {
          this.globalData.userInfo = res.data.data;
          if (!this.globalData.userInfo.communityCodeIcon){
            this.globalData.userInfo.communityCodeIcon = this.globalData.ewm;
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

  },

  globalData: {
    userInfo: null,
    eid: "1",
    ewm: "https://qqxw-pics.oss-cn-beijing.aliyuncs.com/IMAGE/gh_f249f8f03187_430.jpg"
  },
  goSearch: function () {
    wx.navigateTo({
      url: '../search/index',
    })
  }
})