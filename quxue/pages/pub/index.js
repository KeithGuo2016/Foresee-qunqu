// pages/pub/index.js
const app = getApp()
const until = require('../../utils/util.js')
const dateTimePicker = require('../../utils/pick.js')
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
    tagN: 0,
    host: until.host,
    upicon: true,
    startDate: '开始日期',
    endDate:'结束日期',
    date: '2018-10-01',
    time: '12:00',
    dateTimeArray: null,
    dateTime: null,
    dateTimeArray1: null,
    dateTime1: null,
    startYear: 2019,
    endYear: 2050,
    tempFile: '',
    formats: {},
    formats1: {},
    bottom: 0,
    readOnly: false,
    placeholder: '开始输入...',
    _focus: false,
    thisH:400,
    thisH1:400,
    cursor:0,
    changX:false
  },
 
  getTit:function(e){
    
    //this.data.contributeTitle = e.detail.value
    this.setData({
      contributeTitle: e.detail.value,
      
    })
  },
  selTag:function(e){
    if (this.data.tagN>=3){
      wx.showToast({
        title: '最多可选择3个标签',
        duration:2000,
        icon:'none',
      })
      return;
   }
    let tag = "";
    var index = e.currentTarget.dataset.index;
    var tagid = e.currentTarget.dataset.id;
    var item = this.data.itemList[index];
    item.checked = !item.checked;
    this.setData({
      itemList:this.data.itemList,
      tagN: this.data.itemList.filter(item=>{return item.checked}).length
    }) 
   /* for (var i in this.data.itemList.filter(item=>{return item.checked})){
      if (i == 0) tag += this.data.itemList[index].id;
      else tag += `,${this.data.itemList[i].id}`
    }*/
    for (var i in this.data.itemList) {
      if (this.data.itemList[i].checked){
        tag += this.data.itemList[i].id+",";
      }
    }
   
    this.setData({
      contributeTag: tag
    })
    
  },
  radioChangea(e){
    this.setData({
      contributeRange: e.detail.value
    })
  },
  radioChangeb(e) {
    this.setData({
      userSeeRange: e.detail.value
    })
  },
  radioChangec(e) {
    this.setData({
      contributeType: e.detail.value
    })
  },
  getNum:function(e){
    this.setData({
      buyNum: e.detail.value
    })
  },
  minMon:function(e){
    this.setData({
      minMoney: e.detail.value
    })
  },
  maxMon: function (e) {
    this.setData({
      maxMoney: e.detail.value
    })
  },
  insertImage:function() {
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
  insertImage1: function () {
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
            
            that.editorCtx1.insertImage({
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
  pub:function(){
    let createUserid = '';
    let communityId ="";
    if (app.globalData.userInfo!= null){
      createUserid =app.globalData.userInfo.id;
      communityId = app.globalData.userInfo.communityid
    }
    if (this.data.contributeTitle.trim().length <= 0) {
      wx.showToast({
        title: '请填写标题',
        duration: 2000,
        icon: 'none',
      })
      return;
    }
    if (this.data.contributeTag.length <= 0) {
      wx.showToast({
        title: '请选择标签',
        duration: 2000,
        icon: 'none',
      })
      return;
    }
    if (this.data.tempFile.length<=0){
      wx.showToast({
        title: '请上传征稿封面',
        duration: 2000,
        icon: 'none',
      })
      return;
    }
    if (this.data.contributeRange.length <= 0) {
      wx.showToast({
        title: '请确定征稿可见范围',
        duration: 2000,
        icon: 'none',
      })
      return;
    }
    if (this.data.userSeeRange.length <= 0) {
      wx.showToast({
        title: '请确定投放可见范围',
        duration: 2000,
        icon: 'none',
      })
      return;
    }
    if (this.data.buyNum.length <= 0) {
      wx.showToast({
        title: '请确定稿件需求数量',
        duration: 2000,
        icon: 'none',
      })
      return;
    }
    if (this.data.formats.length <= 0) {
      wx.showToast({
        title: '请填写征稿描述',
        duration: 2000,
        icon: 'none',
      })
      return;
    }
    
    let contributeTag ='';
    if (this.data.contributeTag.length>0){
      contributeTag = this.data.contributeTag.substring(0, this.data.contributeTag.length - 1);
    }
    let formats1="";
    if (this.data.formats1.length>0){
      formats1= this.data.formats1;
    }
   
    let data = {
      buyNum: this.data.buyNum,
      createUserid: createUserid,
      communityId: communityId,
      contributeIcon:this.data.tempFile,
      contributeDesc: this.data.formats,
      caseDesc: formats1,
      contributeTitle: this.data.contributeTitle,
      contributeTag: contributeTag,
      contributeRange: this.data.contributeRange,
      userSeeRange: this.data.userSeeRange,
      contributeType: this.data.contributeType,
      maxMoney: this.data.maxMoney,
      minMoney: this.data.minMoney,
      startDate: this.data.startDate+" 00:00:00",
      endDate: this.data.endDate + " 00:00:00"
    };
    until.requestPost('/contribute/insert',data,res=>{
      
      if(res.data.msg == 'OK'){
        wx.showToast({
          title: '发布成功',
        })
        wx.navigateBack({
          delta: 1
        })
      }
    })
  },
  changeDate(e) {
    this.setData({
      date: e.detail.value
    });
  },
  changeTime(e) {
    this.setData({
      time: e.detail.value
    });
  },
  changeDateTime(e) {
    this.setData({
      dateTime: e.detail.value
    });
  },
  changeDateTime1(e) {
    this.setData({
      dateTime1: e.detail.value
    });
  },
  changeDateTimeColumn(e) {
    var arr = this.data.dateTime,
      dateArr = this.data.dateTimeArray;
    arr[e.detail.column] = e.detail.value;
    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);
    this.setData({
      dateTimeArray: dateArr,
      dateTime: arr,
      startDate: `${dateArr[0][arr[0]]}-${dateArr[1][arr[1]]}-${dateArr[2][arr[2]]} ${dateArr[3][arr[3]]}:${dateArr[4][arr[4]]}:${dateArr[5][arr[5]]}`
    });
  },
  changeDateTimeColumn1(e) {
    var arr = this.data.dateTime1,
      dateArr = this.data.dateTimeArray1;
    arr[e.detail.column] = e.detail.value;
    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);

    this.setData({
      dateTimeArray1: dateArr,
      dateTime1: arr,
      endDate: `${dateArr[0][arr[0]]}-${dateArr[1][arr[1]]}-${dateArr[2][arr[2]]}`
    })
    // ${dateArr[3][arr[3]]}:${dateArr[4][arr[4]]}:${dateArr[5][arr[5]]}
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
            imageWidth: 336,
            imageHeight: 142,
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
              tempFile: JSON.parse(data.data).data
            })
          }
        })

      }
    })
  },
  //编辑器
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
 /* undo() {
    this.editorCtx.undo()
  },*/
  /*redo() {
    this.editorCtx.redo()
  },
  format(e) {
    let { name, value } = e.target.dataset
    if (!name) return
    this.editorCtx.format(name, value)

  },*/
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
  onStatusChange1(e) {
    var query = wx.createSelectorQuery();
    query.select('#labeltextarea1').boundingClientRect()

    query.exec((res) => {
      var listHeight = res[0].height; // 获取list高度
      this.setData({
        thisH1: listHeight * 2 - 92,
      })
    })
    const formats1 = e.detail.html
    this.setData({ formats1 })
  },
  /*
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
  */
  //第二个编辑器
  onEditorReady() {
    const that = this
    wx.createSelectorQuery().select('#editor1').context(function (res) {
      that.editorCtx1 = res.context
    }).exec()
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    until.requestGet('/tag/selectContributeTag',{}, res=>{
      if(res.data.msg == 'OK'){
        this.setData({
          itemList:(res.data.data).map(item=>{return Object.assign(item,{'checked':false})})
        })
        console.log(this.data.itemList)
      }
    })
    var obj = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
    var obj1 = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
    // 精确到分的处理，将数组的秒去掉
    //var lastArray = obj1.dateTimeArray.pop();
    //var lastTime = obj1.dateTime.pop();

    this.setData({
      dateTime: obj.dateTime,
      dateTimeArray: obj.dateTimeArray.slice(0,3),
      dateTimeArray1: obj1.dateTimeArray.slice(0, 3),
      dateTime1: obj1.dateTime,
      startDate: `${obj.dateTimeArray[0][obj.dateTime[0]]}-${obj.dateTimeArray[1][obj.dateTime[1]]}-${obj.dateTimeArray[2][obj.dateTime[2]]} `,
      //${obj.dateTimeArray[3][obj.dateTime[3]]}:${obj.dateTimeArray[4][obj.dateTime[4]]}:${obj.dateTimeArray[5][obj.dateTime[5]]}
     
    });
    const that = this
    cropper.init.apply(that, [W, H, 2]);
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
    wx.createSelectorQuery().select('#editor1').context(function (res) {
      that.editorCtx1 = res.context;
      that.editorCtx1.setContents({
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