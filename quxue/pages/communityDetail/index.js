// pages/communityDetail/index.js
const until = require('../../utils/util.js')
const app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    isJoin: '',
    follow: '关注',
    host: until.host,
    tabNav: ['文章', '征稿', '简介', '社刊'],
    curIndex: 2,
    detial: null,
    text: "----------------------------------------------------------------",
    marqueePace: 0.5,//滚动速度
    marqueeDistance: 250,//初始滚动距离
    marquee_margin: 0,
    size: 28,
    interval: 20, // 时间间隔
    applyJoin: false,
    phoneVerify: false,
    showCode: true,
    applyVip: false,
    time: 60,
    applyDesc: ""
  },
  follow: function () {
    let userid = "";
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id
    }
    let data = {
      communitysId: this.data.detail.id,
      userid: userid
    };
    if (this.data.follow == '关注') {
      until.requestPost('/communitysFollow/insert', data, res => {
        if (res.data.msg == 'OK') {
          wx.showToast({
            title: '关注成功',
          })
          this.setData({
            follow: '已关注'
          })
        }
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '确定要取消关注吗?',
        success: res => {
          if (res.confirm) {
            until.requestPost('/communitysFollow/cancelFollow', data, res => {
              if (res.data.msg == 'OK') {
                this.setData({
                  follow: '关注'
                })
                wx.showToast({
                  title: '取消关注',
                })
              }
            })
          }
        }
      })

    }

  },
  tabSwitch: function (e) {
    this.setData({
      curIndex: e.target.dataset.id
    })
  },
  goBack: function (e) {
    let pages = getCurrentPages();
    if(pages.length>1){
      wx.navigateBack();
    }else{
      wx.switchTab({
        url: '../index/index',
      })
    }
    
  },
  detail: function (e) {
    wx.navigateTo({
      url: '../detail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  shekanDetail: function (e) {
    wx.navigateTo({
      url: `../journalDetail/index?id=${e.currentTarget.dataset.id}`,
    })
  },
  collDetail: function (e) {
    wx.navigateTo({
      url: '../collectDetail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  allMembers: function () {
    wx.navigateTo({
      url: '../members/index?id=' + this.data.id,
    })
  },
  join(e) {
    until.checkAuth();
    this.setData({
      applyJoin: true
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
  canApply() {
    this.setData({
      applyJoin: false,
      applyDesc: '',
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
    let r = until.getInviteCode();
    let data = {
      userid: userid,
      userName: this.data.userName,
      phone: this.data.phone,
      qq: this.data.qq,
      code: this.data.code,
      applyDesc: this.data.applyDesc,
      applyTargetId: this.data.detail.id,
      inviteCode: r?r.inviteCode:'',
      eid: r ? r.eid : ''
    };
    until.requestGet('/apply/isAddCommunity', { userid: userid }, res => {
      if (res.data.data.addApply) {
        wx.showToast({
          icon: 'none',
          title: '您的加入社群申请正在审核中，请耐心等待',
          duration: 2000
        })
        return;
      }
      if (res.data.data.create) {
        wx.showToast({
          icon: 'none',
          title: '您提交的创建社群申请正在审核，请耐心等待',
          duration: 2000
        })
        return;
      }
    })
    if (!this.data.detail.id) {
      wx.showToast({
        icon: 'none',
        title: '请选择一个社群重新申请',
      })
    }
    else if (!this.data.userName) {
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
      until.requestPost('/apply/insertAddCommunity', data, res => {
        if (res.data.msg == 'OK') {
          wx.showToast({
            icon: 'none',
            title: '申请成功，请耐心等待审核',
            duration: 2000
          })
        }else if (res.data.status == 500) {
          wx.showToast({
            icon: 'none',
            title: res.data.msg,
            duration: 2000
          })
        }
        this.setData({
          isJoin:1,
          applyJoin: false
        })
      })
    }
  },





















  // 滚动
  scrolltxt: function () {
    var that = this;
    var length = that.data.length;//滚动文字的宽度
    var windowWidth = that.data.windowWidth;//屏幕宽度
    if (length > windowWidth) {
      var interval = setInterval(function () {
        var maxscrollwidth = length + that.data.marquee_margin;//滚动的最大宽度，文字宽度+间距，如果需要一行文字滚完后再显示第二行可以修改marquee_margin值等于windowWidth即可
        var crentleft = that.data.marqueeDistance;
        if (crentleft > -maxscrollwidth) {//判断是否滚动到最大宽度
          that.setData({
            marqueeDistance: crentleft - that.data.marqueePace
          })
        } else {
          //console.log("替换");
          that.setData({
            marqueeDistance: 250 // 直接重新滚动
          });
          clearInterval(interval);
          that.scrolltxt();
        }
      }, that.data.interval);
    } else {
      that.setData({ marquee_margin: "1000" });//只显示一条不滚动右边间距加大，防止重复显示
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(!!options.invite){
      until.setInviteCode(options.ic, options.eid);
    }
    if (!!options.scene) {
      var scene = "&" + decodeURIComponent(options.scene);
      /*wx.showModal({
        title: '提示',
        content: until.getUrlParam(scene, "ic") + "    " + until.getUrlParam(scene, "eid")
      })*/
      until.setInviteCode(until.getUrlParam(scene, "ic"), until.getUrlParam(scene, "eid"));
      options.id = until.getUrlParam(scene, "id");
    }
    until.checkAuth();
    let isJoin = "";
    let userId = "";
    if (app.globalData.userInfo != null) {
      isJoin = app.globalData.userInfo.communityid;
      userId = app.globalData.userInfo.id;
      if (isJoin == options.id) {
        this.setData({
          curIndex: 0
        })
      }
    }
    this.setData({
      isJoin: isJoin,
      id: options.id
    })
    let datas = {
      id: options.id,
      userId: userId
    }
    until.requestGet('/communitys/selectById', datas, res => {

      if (res.data.data.isjion) {
        isJoin = res.data.data.isjion;
      }
      this.setData({
        detail: res.data.data,
        isJoin: isJoin,
        follow: res.data.data.communitysFollow ? '已关注' : '关注'
      })
    })
    //查询用户列表
    let data = {
      communityid: options.id,
      page: 1,
      pageSize: 10
    };
    until.requestGet('/wxuser/getUserListCommunityid', data, res => {
      this.setData({
        memList: res.data.data.slice(0, 10)
      })
    })
    //查询文章列表
    until.requestGet('/articles/selectByCommunityId', data, res => {

      this.setData({
        artList: res.data.data.list,
        artpages: res.data.data.pages,
        artpage: res.data.data.pageNum
      })
    })
    //查询征稿
    until.requestGet('/contribute/selectInCommunityList', data, res => {

      this.setData({
        collList: res.data.data.list,
        collpages: res.data.data.pages,
        collpage: res.data.data.pageNum
      })
    })
    //查询社刊
    until.requestGet('/magazine/selectByCommunityId', data, res => {

      this.setData({
        commList: res.data.data.list,
        commpages: res.data.data.pages,
        commpage: res.data.data.pageNum
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
    var that = this;
    var length = that.data.text.length * that.data.size;//文字长度
    var windowWidth = wx.getSystemInfoSync().windowWidth;// 屏幕宽度
    //console.log(length,windowWidth);
    that.setData({
      length: length,
      windowWidth: windowWidth
    });
    that.scrolltxt();// 第一个字消失后立即从右边出现
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
    //0的时候做文章分页读取
    //1的时候做专题分页读取
    //3的时候做社刊分页读取

    var that = this;
    if (that.data.curIndex == 0) {
      console.log("文章")
      if (that.data.artpage >= that.data.artpages) {
        return;
      }
      //查询文章列表
      until.requestGet('/articles/selectByCommunityId', { communityid: that.data.id, page: that.data.artpage + 1, pageSize: 10 }, res => {
        let cont = that.data.artList;
        for (var i = 0; i < res.data.data.list.length; i++) {
          cont.push(res.data.data.list[i])
        }
        this.setData({
          artList: cont,
          artpages: res.data.data.pages,
          artpage: res.data.data.pageNum
        })
      })
    }

    if (that.data.curIndex == 1) {
      console.log("征稿")
      if (that.data.collpage >= that.data.collpages) {
        return;
      }
      //查询征稿
      until.requestGet('/contribute/selectInCommunityList', { communityid: that.data.id, page: that.data.collpage + 1, pageSize: 10 }, res => {
        let cont = that.data.collList;
        for (var i = 0; i < res.data.data.list.length; i++) {
          cont.push(res.data.data.list[i])
        }
        this.setData({
          collList: cont,
          collpages: res.data.data.pages,
          collpage: res.data.data.pageNum
        })
      })
    }

    if (that.data.curIndex == 3) {
      console.log("社刊")
      if (that.data.commpage >= that.data.commpages) {
        return;
      }
      //查询社刊
      until.requestGet('/magazine/selectByCommunityId', { communityid: that.data.id, page: that.data.commpage + 1, pageSize: 10 }, res => {
        let cont = that.data.commList;
        for (var i = 0; i < res.data.data.list.length; i++) {
          cont.push(res.data.data.list[i])
        }
        this.setData({
          commList: cont,
          commpages: res.data.data.pages,
          commpage: res.data.data.pageNum
        })
      })
    }

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function (res) {


    var shareObj = {
      title: "【群趣创作】" + this.data.detail.communityName,
      path: '/pages/communityDetail/index?id=' + this.data.detail.id + '&ic=' + app.globalData.userInfo.inviteCode + '&eid=' + app.globalData.eid,
      imageUrl: this.data.detail.communityIcon,
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