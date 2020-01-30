
const until = require('../../utils/util.js')
const app = getApp()
const cropper = require('../../welCropper/welCropper.js');
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
    tag: '请选择',
    host: until.host,
    upicon: true,
    tempFile: '',
    formats: {},
    bottom: 0,
    readOnly: false,
    placeholder: '开始输入...',
    _focus: false,
    contributesId: null,
  },
  artitle(e) {
    this.setData({
      title: e.detail.value
    })
  },
  ardesc(e) {
    this.setData({
      desc: e.detail.value
    })
  },
  recom(e) {
    console.log(e.detail.value)
    this.setData({
      recom: e.detail.value ? 1 : 0
    })
  },
  isDraf(e) {
    console.log(e.detail.value)
    this.setData({
      isDraf: e.detail.value ? 1 : 0
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
    let data = e.detail.value,
      tagv = '';
    for (let val of data) {
      tagv += `${val} `;
      for (let i in this.data.itemList) {
        if (this.data.itemList[i].value == val) this.data.itemList[i].checked = true;

      }
    }
    this.setData({
      tag: tagv,
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
          url: until.host + '/img/uploadIconScale',
          filePath: res.tempFiles[0].path,
          name: 'file',
          formData: {
            userId: wx.getStorageSync('openId'),
            imageWidth: 600,
            imageHeight: 600,
          },
          success: data => {
            console.log(data.data)
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
              tempFile: JSON.parse(data.data).data
            })
          }
        })

      }
    })
  },
  pub() {
    wx.showLoading();
    let userid = ""
    if (app.globalData.userInfo != null) {
      userid = app.globalData.userInfo.id;
    }
    let r = until.getInviteCode();
    let data = {
      "deliveryContent": this.data.formats,
      "deliveryIcon": this.data.tempFile,
      "deliveryTitle": this.data.title,
      "userid": userid,
      "contributesId": this.data.contributesId,
      "isSelect": 0,
      "isPay": 0,
      "readNum": 0,
      "inviteCode": r ? r.inviteCode : '',
      "eid": r ? r.eid : ''
    }
    until.requestPost('/delivery/insert', data, res => {
      console.log(res.data)
      if (res.data.msg == 'OK') {
        wx.hideLoading();
        wx.showToast({
          title: '发布成功',
        })
        wx.navigateBack({
          delta: 1
        })
      }

    })
  },
  readOnlyChange() {
    this.setData({
      readOnly: !this.data.readOnly
    })
  },
  onLoad() {
    wx.loadFontFace({
      family: 'Pacifico',
      source: 'url("./assets/Pacifico.ttf")',
      success: console.log
    })
  },
  onEditorReady() {
    const that = this
    wx.createSelectorQuery().select('#editor').context(function (res) {
      console.log(res)
      that.editorCtx = res.context
    }).exec()
  },
  undo() {
    this.editorCtx.undo()
  },
  redo() {
    this.editorCtx.redo()
  },
  format(e) {
    let { name, value } = e.target.dataset
    if (!name) return
    // console.log('format', name, value)
    this.editorCtx.format(name, value)

  },
  onStatusChange(e) {
    var query = wx.createSelectorQuery();
    query.select('#labeltextarea').boundingClientRect()

    query.exec((res) => {
      var listHeight = res[0].height; // 获取list高度
      //246 492
      this.setData({
        thisH: listHeight * 2 - 92,
      })
    })
    const formats = e.detail.html
    this.setData({ formats })
    
  },
  insertDivider() {
    this.editorCtx.insertDivider({
      success: function () {
        console.log('insert divider success')
      }
    })
  },
  clear() {
    this.editorCtx.clear({
      success: function (res) {
        console.log("clear success")
      }
    })
  },
  removeFormat() {
    this.editorCtx.removeFormat()
  },
  insertDate() {
    const date = new Date()
    const formatDate = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`
    this.editorCtx.insertText({
      text: formatDate
    })
  },
  insertImage: function () {
    const that = this
    wx.chooseImage({
      count: 1,
      success: function (res) {
        wx.uploadFile({
          url: until.host + '/img/uploadImage',
          filePath: res.tempFiles[0].path,
          name: 'file',
          formData: {
            userId: wx.getStorageSync('openId')
          },
          success: data => {
            that.editorCtx.insertImage({
              src: JSON.parse(data.data).data,
              data: {
                id: 'abcd',
                role: 'god'
              },
              success: function () {
                console.log('insert image success')
              }
            })
          }
        })

      }
    })
  },
  /*insertImage() {
    const that = this
    wx.chooseImage({
      count: 1,
      success: function (res) {
        wx.uploadFile({
          url: until.host + '/img/uploadIcon',
          filePath: res.tempFiles[0].path,
          name: 'file',
          formData: {
            userId: wx.getStorageSync('openId')
          },
          success: data => {
            that.editorCtx.insertImage({
              src: JSON.parse(data.data).data,
              data: {
                id: 'abcd',
                role: 'god'
              },
              success: function () {
                console.log('insert image success')
              }
            })
          }
        })

      }
    })
  },*/
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
          maxLength: 600, //默认2000，允许最大长宽，避免分辨率过大导致崩溃
          callback: (res) => {
            if (mode == 'rectangle') {
              wx.showLoading();
              wx.uploadFile({
                url: until.host + '/img/uploadImage',
                filePath: res,
                name: 'file',
                formData: {
                  userId: wx.getStorageSync('openId'),
                  imageWidth: 600,
                  imageHeight: 600,
                },
                success: data => {
                  console.log(data.data)
                  wx.hideLoading();

                  if (JSON.parse(data.data).status == 200) {
                    that.setData({
                      upicon: false,
                      tempFile: JSON.parse(data.data).data

                    })
                  }
                  if (JSON.parse(data.data).status == 500) {
                    wx.showToast({
                      icon: 'none',
                      title: JSON.parse(data.data).msg,
                      duration: 3000
                    })
                  }
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
    cropper.init.apply(this, [W, H, 1]);
    this.setData({
      contributesId: options.id,
    })
    const that = this
   
    wx.createSelectorQuery().select('#editor').context(function (res) {
      that.editorCtx = res.context;
      that.editorCtx.setContents({
        html: that.data.result,
        success: (res) => {
          console.log(res)
        },
        fail: (res) => {
          console.log(res)
        }
      })
    }).exec()

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