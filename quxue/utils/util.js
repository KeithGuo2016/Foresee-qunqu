const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const getNowDate = () => {
  const date = new Date();
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('-');
}

const getDay = (days) =>{
  const date = new Date();
  const targetday_milliseconds = date.getTime() + 1000 * 60 * 60 * 24 * days;
  date.setTime(targetday_milliseconds); //注意，这行是关键代码
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  return [year, month, day].map(formatNumber).join('-')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

const curDay = () => {
  var mydate = new Date();
  var dateArr = [getDay(-6), getDay(-5), getDay(-4), getDay(-3), getDay(-2), getDay(-1), getDay(0)];
  var displayDay = [];
  var dayObj = {};
  switch (mydate.getDay()){
    case 0:
      displayDay = ['周一', '周二', '周三', '周四', '周五', '昨天', '今天'];
      break;
    case 1:
      displayDay = ['周二', '周三', '周四', '周五', '周六', '昨天', '今天'];
      break;
    case 2:
      displayDay = ['周三', '周四', '周五', '周六', '周日', '昨天', '今天'];
      break;
    case 3:
      displayDay = ['周四', '周五', '周六', '周日', '周一', '昨天', '今天'];
      break;
    case 4:
      displayDay = ['周五', '周六', '周日', '周一', '周二', '昨天', '今天'];
      break;
    case 5:
      displayDay = ['周六', '周日', '周一', '周二', '周三', '昨天', '今天'];
      break;
    case 6:
      displayDay = ['周日', '周一', '周二', '周三', '周四', '昨天', '今天'];
      break;
  }
  dayObj = {
    dayAry: dateArr,
    displayDay: displayDay
  }
  return dayObj;
}

//const host = 'https://api.yujianmeet.com';
const host = 'https://test-api.yujianmeet.com';
//const host = 'http://localhost:8080';


function loginPost(url, data, fun) {
  wx.request({
    url: host + url,
    data: data,
    method: 'POST',
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    success: res => {
      if (res.data.code == 403) {
        wx.navigateTo({
          url: '../auth/index',
        })
      }else if(res.data.status == 500){
        wx.showToast({
          icon: 'none',
          title: res.data.msg,
        })
      }
      else fun(res)
    },
    fail: err => {
      console.log(err)
      wx.showToast({
        icon: 'none',
        title: '网络错误',
      })
    }
  })
}

function requestPost(url,data,fun){
  wx.request({
    url: host + url,
    data: data,
    method: 'POST',
    header:{
      'content-type': 'application/json;charset=UTF-8'
    },
    success: res =>{
      if(res.data.code == 403){
        wx.navigateTo({
          url: '../auth/index',
        })
      }else if(res.data.status == 500){
        wx.showToast({
          icon: 'none',
          title: res.data.msg,
        })
      }
      else fun(res)
    },
    fail: err =>{
      console.log(err)
      wx.showToast({
        icon: 'none',
        title: '网络错误',
      })
    }
  })
}

function requestGet(url, data, fun) {
  wx.showLoading();
  wx.request({
    url: host + url,
    data: data,
    method: 'GET',
    header: {
      "Content-Type": "application/json;charset=UTF-8"
    },
    success: res => {
      if(res.statusCode == 200){
        wx.hideLoading();
        fun(res)
      }
      else{
        wx.showToast({
          icon: 'none',
          title: '请求出错了！'
        })
      }
    },
    fail: err => {
      console.log(err)
      wx.showToast({
        icon:'none',
        title: '网络错误'
      })
    }
  })
}
function getUrlParam(url, name) {
  // 正则筛选地址栏
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
  // 匹配目标参数
  var result = url.substr(1).match(reg);
  //返回参数值
  return result ? decodeURIComponent(result[2]) : null;
}

function getInviteCode(){
  let result = wx.getStorageSync('inviteCode');
  if(!!result){
    result = JSON.parse(result);
    if (new Date().getTime()<=result.life){
      return result;
    }else{
      wx.removeStorageSync('inviteCode');
    }
  }
  return null;
}

function setInviteCode(inviteCode, eid){
  let result = wx.getStorageSync('inviteCode');
  if (!!result) {
    result = JSON.parse(result);
    if (new Date().getTime() <= result.life) {
      return;
    }
  }
  const life = new Date().getTime() + 1000 * 60 * 60 * 24 * 3;
  result = {
    'life': life,
    'inviteCode': inviteCode,
    'eid': eid
  }
  wx.setStorageSync('inviteCode', JSON.stringify(result));
}
function checkAuth(){
  if (!wx.getStorageSync('openId')) wx.redirectTo({
    url: '../auth/index?rt=' + encodeURIComponent(getCurrentPageUrlWithArgs()),
  })
}
function getCurrentPageUrlWithArgs() {
  const pages = getCurrentPages()
  const currentPage = pages[pages.length - 1]
  const url = currentPage.route
  const options = currentPage.options
  let urlWithArgs = `/${url}?`
  for (let key in options) {
    const value = options[key]
    urlWithArgs += `${key}=${value}&`
  }
  urlWithArgs = urlWithArgs.substring(0, urlWithArgs.length - 1)
  return urlWithArgs
}
module.exports = {
  formatTime: formatTime,
  getNowDate: getNowDate,
  curDay: curDay,
  host: host,
  loginPost: loginPost,
  requestPost: requestPost,
  requestGet: requestGet,
  checkAuth: checkAuth,
  getCurrentPageUrlWithArgs: getCurrentPageUrlWithArgs,
  getUrlParam: getUrlParam,
  getInviteCode: getInviteCode,
  setInviteCode: setInviteCode
}
