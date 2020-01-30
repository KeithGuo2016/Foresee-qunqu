// pages/creatCommunity/index.js
const until = require('../../utils/util.js')
const app = getApp();
var WxParse = require('../wxParse/wxParse.js');
let cropper = require('../../welCropper/welCropper.js');
const device = wx.getSystemInfoSync()
const W = device.windowWidth
const H = device.windowHeight - 50

Page({
  /**
   * 页面的初始数据
   */
  data: {
    selTag: '',
    itemList: [],
    showCode: true,
    tag: '请选择',
    upicon: true,
    tempFile: '',
    index:0,
    //array: [],
    //objectArray: [],
    communityName:'',
    communityType:'',
    communityDesc: '', 
    qq: '',
    ids:'',
    getphone:false,
    time:60,
    desclength: 0,
    markLength: 0,
      },

  communityName(e) {
    this.setData({
      communityName: e.detail.value
    })
  },
  getQQ: function (e) {
    this.setData({
      qq: e.detail.value
    })
  },
  communityDesc(e) {
    this.setData({
      communityDesc: e.detail.value,
      desclength: e.detail.value.length,
    })
  },
  communityNotice(e){
    this.setData({
      communityNotice: e.detail.value
    })
  },
  communityMark(e) {
    this.setData({
      communityMark: e.detail.value,
      markLength: e.detail.value.length
    })
  },
  recom(e) {
    console.log(e.detail.value)
    this.setData({
      recom: e.detail.value ? 1 : 0
    })
  },
  
  selectTag() {
    this.setData({
      selTag: true
    })
  },
  checkboxChange: function (e) {
    for (let i in this.data.itemList) {
      this.data.itemList[i].checked = false;
    }
    let tagv = '';  
    let ids='';
    for (var i = 0; i < e.detail.value.length; i++) {
      let data = e.detail.value[i].split(',');
      tagv += data[0]+' ';
      ids += data[1]+',';
      for (let i in this.data.itemList) {
        if (this.data.itemList[i].id == data[1]) this.data.itemList[i].checked = true;

      }
    }
    this.setData({
      tag: tagv,
      ids:ids,
      itemList: this.data.itemList
    })
  },
  cancel() {
    this.setData({
      selTag: false,
      tag: '请选择'
    })
  },
  
  selok() {
    this.setData({
      selTag: false
    })
  },
  chooseImg() {
    wx.chooseImage({
      count: 1,
      sizeType: ['compressed'],
      success: res => {
        wx.showLoading();
        wx.uploadFile({
          url: until.host + '/img/uploadIconCommunity',
          filePath: res.tempFiles[0].path,
          name: 'file',
          formData: {
            userId: wx.getStorageSync('openId'),
            imageWidth: 700,
            imageHeight: 700,
          },
          success: data => {
            
            
            wx.hideLoading();
            if (JSON.parse(data.data).status == 500) {
              wx.showToast({
                icon: 'none',
                title: JSON.parse(data.data).msg,
                duration: 3000
              })
              return;
            }
            this.setData({
              upicon: false,
              tempFile: JSON.parse(data.data).data.iconPath,
              bgIcon: JSON.parse(data.data).data.bgImg,
            })
          }
        })

      }
    })
  },
  pub:function(){
    if (!this.data.communityName.trim()){
      wx.showToast({
        icon:'none',
        title: '请填写社群名称',
        duration:3000,
      })
      return;
    }
    if (!this.data.qq) {
      wx.showToast({
        icon: 'none',
        title: '请输入QQ或者微信！',
        duration: 3000,
      })
      return;
    }
    if (this.data.tempFile.length<=0) {
      wx.showToast({
        icon: 'none',
        title: '请上传社群图标',
        duration: 3000,
      })
      return;
    }
    if (this.data.communityDesc.length <= 0) {
      wx.showToast({
        icon: 'none',
        title: '请填写社群说明',
        duration: 3000,
      })
      return;
    }
    if (this.data.tag.length <= 0 || this.data.tag == '请选择') {
      wx.showToast({
        icon: 'none',
        title: '请选择社群标签',
        duration: 3000,
      })
      return;
    }
    let ids = this.data.ids.substring(0, this.data.ids.length - 1)
    wx.showLoading();
    let data = {
      "communityName": this.data.communityName,
      "communityDesc": this.data.communityDesc,
      "communityIcon": this.data.tempFile,
      "communityNotice": this.data.communityNotice,
      "communityMark": this.data.communityMark,
      "communityTag": ids,
      "qq": this.data.qq,
      "bgIcon": this.data.bgIcon,
      //"communityType": this.data.communityType,
      "adminId": app.globalData.userInfo.id,
      "createdBy": app.globalData.userInfo.id
    }
    until.requestPost('/communitys/insert', data, res => {
      
      if (res.data.msg == 'OK') {
        wx.hideLoading();
        wx.showToast({
          icon: 'none',
          title: '请耐心等待审核',
          duration: 2000
        })
        wx.navigateBack({
          delta: 1
        })
      } else if (res.data.status == 500) {
        wx.hideLoading();
        wx.showToast({
          icon: 'none',
          title: res.data.msg,
          duration: 2000
        })
      }

    })
  },
  canApply() {
    this.setData({
      getphone: false,
    })
    wx.navigateBack({
      delta: 1
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
    var that=this;
    let inter;
    let data = {
      userid: app.globalData.userInfo.id,
      phone: this.data.phone
    };
    until.loginPost('/apply/getCodeUnique', data, res => {
      console.log(res)
    })
    if (that.data.phoneVerify) {
      that.setData({
        showCode: false
      })
      inter = setInterval(() => {
        
        that.data.time--;
        if (that.data.time >= 0) {
          this.setData({
            time: that.data.time--
          })
        }
        else {
          clearInterval(inter)
          that.setData({
            time: 60,
            showCode: true
          })
        }
      }, 1000)

    }
  },
  codeNum: function (e) {
    if ((/^\d{6}$/).test(e.detail.value)) this.setData({
      code: e.detail.value,
      showCode:false,
    })
  },
  goApply: function () {
    let data = {
      id: app.globalData.userInfo.id,
      phone: this.data.phone,
      code: this.data.code,
      openId: app.globalData.userInfo.openId,
    }
    
    if (!this.data.phone) {
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
      until.requestPost('/wxuser/updatePhone', data, res => {
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
        app.globalData.userInfo.phone = this.data.phone;
        this.setData({
          applyVip: false,
          getphone:false
        })
      })
    }
  },
  selectTap(e) {
    let that = this
    let mode = e.currentTarget.dataset.mode
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success(res) {
        const tempFilePath = res.tempFilePaths[0]
        that.showCropper({
          src: tempFilePath,
          mode: mode,
          sizeType: ['original', 'compressed'],   //'original'(default) | 'compressed'
          maxLength: 700, //默认2000，允许最大长宽，避免分辨率过大导致崩溃
          callback: (res) => {
            if (mode == 'rectangle') {
              wx.showLoading();
              wx.uploadFile({
                url: until.host + '/img/uploadIconCommunity',
                filePath: res,
                name: 'file',
                formData: {
                  userId:wx.getStorageSync('openId'),
                  imageWidth: 700,
                  imageHeight: 700,
                },
                success: data => {
                  wx.hideLoading();
                  if (JSON.parse(data.data).status == 500) {
                    wx.showToast({
                      icon: 'none',
                      title: JSON.parse(data.data).msg,
                      duration: 3000
                    })
                    return;
                  }
                  console.log(JSON.parse(data.data).data.iconPath)
                  that.setData({
                    upicon: false,
                    tempFile: JSON.parse(data.data).data.iconPath,
                    bgIcon: JSON.parse(data.data).data.bgImg,
                  })
                }
              })
            }
            else {
              wx.showModal({
                title: '',
                content: JSON.stringify(res),
              })

              console.log(res)
            }

            that.hideCropper() //隐藏，我在项目里是点击完成就上传，所以如果回调是上传，那么隐藏掉就行了，不用previewImage
          }
        })
      }
    })
  },
  
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    until.requestGet('/tag/selectCommunitysTag',{},res=>{
      
      var data = res.data.data;
      for (var i in data) {
        this.data.itemList.push({ id: data[i].id, value: data[i].tagName, checked: '' })
      }
      this.setData({
        itemList: this.data.itemList
      })
    })
   

    var that = this;
    cropper.init.apply(that, [W, H, 1]);
    if (app.globalData.userInfo.phone.length <= 0) {
      wx.showModal({
        title: '提示',
        content: '请填写手机号码',
        confirmText: '确定',
        success(res) {
          if (res.confirm) {
            that.setData({
              getphone: true,
            })

          } else if (res.cancel) {
            wx.navigateBack({
              delta: 1
            })
          }
        }
      })
    }
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