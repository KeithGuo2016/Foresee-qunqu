// pages/gather/index.js
const until = require('../../utils/util.js')
const app = getApp()
const device = wx.getSystemInfoSync()
const W = device.windowWidth
const H = device.windowHeight - 50
let cropper = require('../../welCropper/welCropper.js');
Page({
  /**
   * 页面的初始数据
   */
  data: {
    editor: false,
    page: 1,
    pageSize: 10,
    pages: 1,
    upicon: true,
    tempFile: '',
    getherid: '',
    index:"",
    articleList: [],
    gatherName: '',
    isNew: false

  },
  getMyGather() {
    let userid = "";
    if (app.globalData.userInfo) {
      userid = app.globalData.userInfo.id;
    }
    let data = {
      userid: userid,
      page: this.data.page,
      pageSize: this.data.pageSize
    }
    until.requestGet('/gather/selectMyGather', data, res => {
      console.log(res)
      this.setData({
        list: res.data.data.list
      })
    })

  },
  goDetail:function(e){
    wx.navigateTo({
      url: '../gatherDetail/index?id=' + e.currentTarget.dataset.id, 
    })
  },
  newgather: function() {
    this.setData({
      editor: true,
      isNew: true,
      gatherName: "",
      tempFile: "",
      upicon: true,
    })
  },
  gcancel: function() {
    this.setData({
      editor: false
    })
  },
  editor: function(e) {
    let list = this.data.list;
    var that = this
    wx.showActionSheet({
      itemList: ['编辑内容', '删除文集'],
      success(res) {
        console.log(res.tapIndex)
        if (res.tapIndex == 0) {
          
          that.setData({
            editor: true,
            upicon: false,
            getherid: e.currentTarget.dataset.id,
            index: e.currentTarget.dataset.index,
            gatherName: list[e.currentTarget.dataset.index].gatherName,
            tempFile: list[e.currentTarget.dataset.index].gatherIcon,
          })
          let data = {
            userid: app.globalData.userInfo.id,
            gatherId: e.currentTarget.dataset.id
          }
          until.requestGet('/articles/selectGatherArticles', data, res => {
            that.setData({
              articleList: res.data.data
            })
          })
        }
        if (res.tapIndex == 1) {
          let data = {
            userid: app.globalData.userInfo.id,
            id: e.currentTarget.dataset.id
          }
          until.requestGet('/gather/delete', data, res => {
            list.splice(e.currentTarget.dataset.index, 1);
            that.setData({
              list: list
            })
          })
        }
      },
      fail(res) {
        console.log(res.errMsg)
      }
    })
  },
  selArticle: function(e) {

    wx.navigateTo({
      url: '../addArticle/index?id=' + e.currentTarget.dataset.id + '&index=' + e.currentTarget.dataset.index,
    })
  },


  getName: function(e) {
    this.setData({
      gatherName: e.detail.value
    })

  },
  chooseImg: function() {
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
            imageWidth: 154,
            imageHeight: 239,
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


              /*wx.previewImage({
                current: '',
                urls: [res]
              })*/
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
  delArticle: function(e) {
    let list = this.data.articleList;
    let tlist = this.data.list;
    let that = this;
    let data = {
      delId: e.currentTarget.dataset.id,
      userid: app.globalData.userInfo.id
    }
    until.requestGet('/articles/delArticleToGatherId', data, res => {
      list.splice(e.currentTarget.dataset.index, 1);
      if (!that.data.isnew){
        tlist[that.data.index].articleCount--;
      }
      that.setData({
        articleList: list,
        list: tlist
      })
    })
  },
  goadd: function() {
    if (!this.data.isNew){
      let data = {
        gatherName: this.data.gatherName,
        gatherIcon: this.data.tempFile,
        userid: app.globalData.userInfo.id,
        id: this.data.getherid,
      }
      let list = this.data.list;
      let index = this.data.index;
      until.requestGet('/gather/update', data, res => {
        list[index].gatherName = this.data.gatherName;
        list[index].gatherIcon = this.data.tempFile;
        this.setData({
          list: list,
          editor: false
        })

      })
     
      return;
    }
    if (this.data.gatherName.length <= 0) {
      wx.showToast({
        icon: 'none',
        title: '请填写文集名称',
        duration: 3000,
      })
      return;
    }
    if (this.data.tempFile.length <= 0) {
      wx.showToast({
        icon: 'none',
        title: '请上传文集封面',
        duration: 3000,
      })
      return;
    }
    let list = this.data.articleList;
    let ids="";
    for (var i = 0; i < list.length; i++) {
      ids = ids + list[i].id+",";
    }
    if (ids.length > 0) {
      ids = ids.substring(0, ids.length - 1);
    }
    let data = {
      gatherName: this.data.gatherName,
      gatherIcon: this.data.tempFile,
      userid: app.globalData.userInfo.id,
      ids: ids,
    }
    
    let lista = this.data.list;
   
   
    until.requestGet('/gather/insert', data, res => {
      console.log(res)
      lista.unshift(res.data.data)
      this.setData({
        editor: false,
        list: lista
      })
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    cropper.init.apply(this, [W, H, 0.65]);
    this.getMyGather();
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
    var that = this;
    if (that.data.page >= that.data.pages) {

      return;
    }
    let data = {
      userid: app.globalData.userInfo.id,
      page: this.data.page + 1,
      pageSize: this.data.pageSize
    }
    until.requestGet('/gather/selectMyGather', data, res => {
      console.log(res.data.data)
      let cont = that.data.list;
      for (var i = 0; i < res.data.data.list.length; i++) {
        cont.push(res.data.data.list[i])
      }
      that.setData({
        list: cont,
        pages: res.data.data.pages,
        page: res.data.data.pageNum
      })

    })

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})