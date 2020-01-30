// pages/members/index.js
const until = require('../../utils/util.js')
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  goSearch: function() {
    app.goSearch();
  },
  guide: function(e) {
    this.setData({
      curIndex: e.target.dataset.id
    })
  },
  memDetail: function(e) {
    wx.navigateTo({
      url: '../memberDetail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  
  data: {
    direct: '',
    guArr: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '#'],
    toView: 1,
    curIndex: 0,
    productList: [],
    list: [],
    showCode: true,
    applyPub:false,
  },
  applyAdmin: function () {
    if (app.globalData.userInfo.isAdmin){
      wx.showToast({
        icon: 'none',
        title: '您已经是管理员了！',
      })
      return
    }
    if (app.globalData.userInfo.isCommunity) {
      wx.showToast({
        icon: 'none',
        title: '您已经是社长了！',
      })
      return
    }
    if (app.globalData.userInfo.communityid != this.data.communityid) {
      wx.showToast({
        icon: 'none',
        title: '只有本社群的成员才能申请',
      })
      return
    }
    this.setData({
      applyPub:true,
    })
  },
  canApply() {
    this.setData({
      applyPub: false
    })
  },
  codeNum: function (e) {
    if ((/^\d{6}$/).test(e.detail.value)) this.setData({
      code: e.detail.value
    })
  },
  goApply:function(){
    let data = {
      userid: app.globalData.userInfo.id,
      userName: this.data.userName,
      phone: this.data.phone,
      code: this.data.code,
      applyDesc: this.data.applyDesc,
      applyTargetId:this.data.communityid
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
      until.requestPost('/apply/insertAdmin', data, res => {
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
  getApply: function (e) {
    console.log(e)
    this.setData({
      applyDesc: e.detail.value
    })
  },
  getName: function (e) {
    this.setData({
      userName: e.detail.value
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
  toViewPinyin: function(e) {

    let i = parseInt(e.currentTarget.dataset.i) + 2;
    this.setData({
      toView: i,
      curIndex: i - 2
    })
  },

  /**
   * 显示删除按钮
   */
  showDeleteButton: function(e) {
    let productIndex = e.currentTarget.dataset.productindex;
    let idx = e.currentTarget.dataset.productidx;
    this.setXmove(productIndex, idx, -160);
  },

  /**
   * 隐藏删除按钮
   */
  hideDeleteButton: function(e) {
    
    let productIndex = e.currentTarget.dataset.productindex
    let idx = e.currentTarget.dataset.productidx;
    this.setXmove(productIndex, idx, 0)
  },

  /**
   * 设置movable-view位移
   */
  setXmove: function (productIndex, idx, xmove) {
    let productList = this.data.list;
    productList[productIndex].userList[idx].uid = xmove
    this.setData({
      list: productList,

    })
  },

  /**
   * 处理movable-view移动事件
   */
  handleMovableChange: function(e) {
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
  },

  /**
   * 处理touchend事件
   */
  handleTouchEnd(e) {
   
    if (e.changedTouches[0].pageX < this.startX && e.changedTouches[0].pageX - this.startX <= -30) {
      this.showDeleteButton(e)
    } else if (e.changedTouches[0].pageX > this.startX && e.changedTouches[0].pageX - this.startX < 30) {
      this.showDeleteButton(e)
    } else {
      this.hideDeleteButton(e)
    }
  },
  cancelAdmin: function({
    currentTarget: {
      dataset: {
        id
      }
    }
  }) {
    let data = {
      id: id,
      openid: wx.getStorageSync('openId'),
      isAdmin: 0
    };
    until.requestPost('/wxuser/update', data, res => {
      console.log(res.data)
      if (res.data.msg == "OK") {
        wx.showToast({
          title: '已取消',
        })
        this.getUser();
      }
    })
  },
  setAdmin: function({

    currentTarget: {
      dataset: {
        comun,
        admin,
        id,
        openid
      }
    }
  }) {
    if (admin) admin = 0;
    else admin = 1;
    let data = {
      id: id,
      openId: openid,
      isCommunity: comun,
      isAdmin: admin
    };
    if (comun) {
      wx.showToast({
        icon: 'none',
        title: '已经是社长不能再成为管理员',
      })
    } else {
      until.requestPost('/wxuser/setAdmin', data, res => {
        console.log(res.data)
        if (res.data.msg == "OK") {
          wx.showToast({
            title: '操作成功',
          })
          setTimeout(() => {
            this.getUser()
          }, 2000);
        }
      })
    }
  },
  deleteUser:function(e){
    let id =e.currentTarget.dataset.id;
    let openid = e.currentTarget.dataset.openid;
    until.requestPost('/wxuser/tichu', { id: id, communityid: '', openId: openid}, res => {
     
      if (res.data.msg == "OK") {
        this.getUser()
        wx.showToast({
          title: '操作成功',
        })
       
      }
    })
  },
  getUser: function() {
    let cont = this.data.list;
    let data = {
      communityid: this.data.communityid,
      page: 1,
      pageSize: 10
    };
    until.requestGet('/wxuser/getCommunityUser', data, res => {

      this.setData({
        list: res.data.data,
        //memList: res.data.data.userList,
        productList: res.data.data.userList,


      })
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    until.checkAuth();
    if (app.globalData.userInfo.isCommunity) {
      /*this.setData({
        direct: 'horizontal'
      })*/
    }
    //查询用户列表
    this.setData({
      communityid: options.id
    })
    this.getUser();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})