const until = require('../../utils/util.js')
const app = getApp()
var WxParse = require('../wxParse/wxParse.js');
const cropper = require('../../welCropper/welCropper.js');
const device = wx.getSystemInfoSync()
const W = device.windowWidth
const H = device.windowHeight - 50

//console.log(device)
Page({
  /**
   * 页面的初始数据
   */
  data: {
    selTag: '',
    height: '100rpx',
    itemList: [],
    tag: '请选择',
    host: until.host,
    upicon: true,
    tempFile: '',
    formats: '',
    bottom: 0,
    readOnly: false,
    placeholder: '开始输入...',
    _focus: false,
    ids: '',
    title: '',
    istemp: false,
    detail: null,
    result: null,
    recom: 0,
    ispub: false,
    height2: '54',
  },
  artitle(e) {

    this.setData({
      title: e.detail.value.replace(/\s+/g, '')
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
      recom: e.detail.value.length ? 1 : 0
    })
  },
  isDraf(e) {
    console.log(e.detail.value.length)
    this.setData({
      isDraf: e.detail.value.length ? 1 : 0
    })
    console.log(this.data.isDraf)
  },
  selectTag() {
    this.setData({
      selTag: true,
      height: 0,
      height2: 0
    })
  },
  checkboxChange: function (e) {
    for (let i in this.data.itemList) {
      this.data.itemList[i].checked = false;
    }
    let tagv = '';
    let ids = '';
    for (var i = 0; i < e.detail.value.length; i++) {
      let data = e.detail.value[i].split(',');
      tagv += data[0] + ' ';
      ids += data[1] + ',';
      for (let i in this.data.itemList) {
        if (this.data.itemList[i].id == data[1]) this.data.itemList[i].checked = true;

      }
    }
    this.setData({
      tag: tagv,
      ids: ids,
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
      selTag: false,
      height: '100rpx',
      height2: '54'
    })
  },
  chooseImg() {
    wx.chooseImage({
      count: 1,
      sizeType: ['compressed'],
      success: res => {
        wx.showLoading();
        wx.uploadFile({
          url: until.host + '/img/uploadIcon',
          filePath: res.tempFiles[0].path,
          name: 'file',
          formData: {
            userId: wx.getStorageSync('openId'),
            imageWidth: 348,
            imageHeight: 180,
          },
          success: data => {
            console.log(data.data)
            wx.hideLoading();

            if (JSON.parse(data.data).status == 200) {
              this.setData({
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
    })
  },


  pub(e) {
    this.setData({
      ispub: true,
    })
    if (this.data.title.length <= 0) {
      wx.showToast({
        icon: 'none',
        title: '请编写文章标题',
        duration: 3000,
      })
      return;
    }
    if (this.data.title.length > 15) {
      wx.showToast({
        icon: 'none',
        title: '标题长度不能超过十五个字',
        duration: 3000,
      })
      return;
    }
    let isDraft = e.currentTarget.dataset.type

    if (!isDraft) {
      if (this.data.desc.length <= 0) {
        wx.showToast({
          icon: 'none',
          title: '请填写文章简述',
          duration: 3000,
        })
        return;
      }
      if (this.data.tempFile.length <= 0) {
        wx.showToast({
          icon: 'none',
          title: '请上传文章封面',
          duration: 3000,
        })
        return;
      }
      if (this.data.formats.length <= 0) {
        wx.showToast({
          icon: 'none',
          title: '请编写文章内容',
          duration: 3000,
        })
        return;
      }
    }
    wx.showLoading();
    let ids = "";
    if (this.data.ids.length > 0) {
      ids = this.data.ids.substring(0, this.data.ids.length - 1);
    }

    let data = {
      "articleContent": this.data.formats,
      "articleIcon": this.data.tempFile,
      "articleOutline": this.data.desc,
      "articleTag": ids,
      "articleTitle": this.data.title,
      "isDraft": isDraft,
      "isRecommend": this.data.recom,
      "openid": wx.getStorageSync('openId')
    }
    let url = '/articles/insert';
    if (this.data.istemp) {
      url = '/articles/update';
      data = {
        "id": this.data.detail.id,
        "articleContent": this.data.formats,
        "articleIcon": this.data.tempFile,
        "articleOutline": this.data.desc,
        "articleTag": ids,
        "articleTitle": this.data.title,
        "isDraft": isDraft,
        "isRecommend": this.data.recom,
        "openid": wx.getStorageSync('openId')
      }
    }
    until.requestPost(url, data, res => {
      console.log(res.data)
      if (res.data.msg == 'OK') {
        wx.hideLoading();
        wx.showToast({
          title: '发布成功，请等待审核通过',
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
  onEditorReady() {

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
    const formats = e.detail.html
    this.setData({ formats })
    console.log(e)
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
  insertImage() {
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

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //查询文章标签
    until.requestGet('/tag/selectArticlesTag', {}, res => {
      var data = res.data.data;
      for (var i in data) {
        this.data.itemList.push({ id: data[i].id, value: data[i].tagName, checked: '' })
      }
      this.setData({
        itemList: this.data.itemList
      })
    })
    let that = this;
    cropper.init.apply(that, [W, H, 1]);
    if (options.id) {
      let data = {
        id: options.id,
        userid: app.globalData.userInfo.id,
      }
      until.requestGet('/articles/selectVoById', data, res => {
        console.log(res.data.data)
        this.setData({
          detail: res.data.data,
          istemp: true,

          tag: res.data.data.articleTagNmae,
          ids: res.data.data.articleTag + ",",
          isDraf: 1,
          tempFile: res.data.data.articleIcon,
          formats: res.data.data.articleContent,
          result: res.data.data.articleContent,
          title: res.data.data.articleTitle,
          desc: res.data.data.articleOutline,
          recom: res.data.data.isRecommend,

        })
        if (this.data.tempFile.length > 0) {
          this.setData({
            upicon: false
          })
        }
        const that = this
        wx.createSelectorQuery().select('#editor').context(function (res) {
          // console.log(that.data.result)
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
        // WxParse.wxParse('content', 'html', res.data.data.articleContent, this, 5);
      })


    }

    if (!app.globalData.userInfo.communityid) {
      wx.showModal({
        title: '提示',
        content: '请您先加入社群才可以发布文章',
        confirmText: '选择社群',
        success(res) {
          if (res.confirm) {
            wx.reLaunch({
              url: '../community/index'
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

    if (this.data.ispub) {
      return;
    }
    if (this.data.title.length <= 0) {
      return;
    }
    let ids = "";
    if (this.data.ids.length > 0) {
      ids = this.data.ids.substring(0, this.data.ids.length - 1);
    }
    let data = {};
    if (this.data.detail) {
      data = {
        "articleContent": this.data.formats,
        "articleIcon": this.data.tempFile,
        "articleOutline": this.data.desc,
        "articleTag": ids,
        "id": this.data.detail.id,
        "articleTitle": this.data.title,
        "isDraft": 1,
        "isRecommend": this.data.recom,
        "openid": wx.getStorageSync('openId')
      }
    } else {
      data = {
        "articleContent": this.data.formats,
        "articleIcon": this.data.tempFile,
        "articleOutline": this.data.desc,
        "articleTag": ids,

        "articleTitle": this.data.title,
        "isDraft": 1,
        "isRecommend": this.data.recom,
        "openid": wx.getStorageSync('openId')
      }
    }


    until.requestPost('/articles/insert', data, res => {
      console.log(res.data)
    })


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