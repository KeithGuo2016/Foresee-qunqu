// pages/collectDetail/index.js
const until = require('../../utils/util.js');
const app = getApp();
var WxParse = require('../wxParse/wxParse.js');
Page({
  /**
   * 页面的初始数据
   * 
   */
  data: {
    host: until.host,
    applyVip: false,
    time: 60,
    phoneVerify: false,
    showCode: true,
    isVip: false,
    pageSize: 3,
    //投稿总数
    deliveryNum: 0,
    //投稿信息列表
    deliveryList: null,
    //投稿总数
    count: 0,
    isShowCase: false,
    communityId: null
  },
  pub(e) {
    if (this.data.detail.contributeRange==2 && !this.data.isVip) {
      //跳转到VIP申请，
      this.setData({
        applyVip: true
      })
      return;
    }

    if (app.globalData.userInfo.communityid <= 0) {
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
    if ((+this.data.detail.contributeRange) == 1 && app.globalData.userInfo.communityid != (+this.data.detail.communityId)) {
      wx.showToast({
        icon: 'none',
        title: '您不是该社群成员，不能参与征稿',
        duration: 2000,
      })
      return;
    }
    wx.navigateTo({
      url: '../putCollect/index?id=' + e.currentTarget.dataset.id,
    })
  },
  canApply() {
    this.setData({
      applyVip: false,
      applyDesc: '',
    })
  },
  detail: function (e) {
    if ((+this.data.detail.userSeeRange) && this.data.detail.createUserid != app.globalData.userInfo.id) {
      wx.showToast({
        icon: 'none',
        title: '投稿内容仅征稿方可见',
        duration: 2000,
      })
      return;
    }
    wx.navigateTo({
      url: '../deliveryDetail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  getName: function (e) {
    this.setData({
      userName: e.detail.value
    })
  },
  getQQ: function (e) {
    this.setData({
      qq: e.detail.value
    })
  },
  getApply: function (e) {

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
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      userid: userid,
      phone: this.data.phone
    };
    until.loginPost('/apply/getCode', data, res => {
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

    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id
    }
    let data = {
      userid: userid,
      userName: this.data.userName,
      phone: this.data.phone,
      code: this.data.code,
      qq: this.data.qq,
      applyDesc: this.data.applyDesc
    }
    if (!this.data.userName) {
      wx.showToast({
        icon: 'none',
        title: '请输入姓名！',
      })
    }
    else if (!this.data.qq) {
      wx.showToast({
        icon: 'none',
        title: '请输入QQ或者微信！',
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
    else if (this.data.applyDesc.length <= 0) {
      wx.showToast({
        icon: 'none',
        title: '请填写申请说明！',
      })
    }
    else {
      until.requestPost('/apply/insertVip', data, res => {
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
          applyVip: false
        })
      })
    }
  },
  //查看更多征稿内容
  goMore: function (e) {
    wx.navigateTo({
      url: '../deliveryList/index?id=' + e.currentTarget.dataset.id + "&createUserid=" + this.data.detail.createUserid + "&userSeeRange=" + this.data.detail.userSeeRange,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (!!options.invite) {
      until.setInviteCode(options.ic, options.eid);
    }
    if (!!options.scene) {
      var scene = "&" + decodeURIComponent(options.scene);
      until.setInviteCode(until.getUrlParam(scene, "ic"), until.getUrlParam(scene, "eid"));
    }
    until.checkAuth();
    var that = this
    let isVip = false;
    if (app.globalData.userInfo != null) {
      isVip = app.globalData.userInfo.isVip;
    }
    this.setData({
      isVip: isVip,
      communityId: app.globalData.userInfo.communityid
    })
    let data = {
      id: options.id,
      pageSize: this.data.pageSize
    };
    until.requestGet('/contribute/selectById', data, res => {
      let isShowCase = false;
      if (res.data.data.caseDesc.length > 0) {
        isShowCase = true

      }
      this.setData({
        detail: res.data.data,
        deliveryNum: res.data.data.info.total,
        deliveryList: res.data.data.info.list,
        count: res.data.data.info.total,
        isShowCase: isShowCase,
      })
      WxParse.wxParse('desc', 'html', res.data.data.contributeDesc, that, 5);
      WxParse.wxParse('case', 'html', res.data.data.caseDesc, that, 5);
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
  onShareAppMessage: function (res) {
    var shareObj = {
      title: "【群趣创作】" + this.data.detail.contributeTitle,
      path: '/pages/collectDetail/index?id=' + this.data.detail.id + '&ic=' + app.globalData.userInfo.inviteCode + '&eid=' + app.globalData.eid,
      imageUrl: this.data.detail.contributeIcon,
      complete: function () {
        return;
      }
    };
    // 来自页面内的按钮的转发
    if (res.from == 'button') {

    }
    // 返回shareObj
    return shareObj;
  }
})