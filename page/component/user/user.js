// page/component/new-pages/user/user.js
//获取应用实例
const app = getApp()
Page({
  data:{
    thumb:'',
    nickname:'',
    viewShowed: "false", //控制授权是否显示
    typeInfo:[]
  },
  openMsg: function () {
    wx.navigateTo({
      url: '../msg/msg'
    })
  },
  onLoad(){
    var self = this;
    wx.stopPullDownRefresh();  //停止下拉刷新
  },
  onShow(){
   
  },
  navClick(e){
    var self = this;
    var user = wx.getStorageSync('user');
    // if(user.openid == undefined){
    //   self.setData({
    //     viewShowed: "",
    //   })
    //   return;
    // }
    wx.request({
      url: app.globalData.reqUrl + 'user/auth',
      method: 'post',
      data: {openId: user.openid},
      header: { 'content-type': 'application/json' },
      success(res) {
        if (res.data.code == "9000") {
          wx.setStorageSync("usertype", res.data.state);
          wx.setStorageSync("users", res.data.userInfo);
          if (res.data.userInfo.rId == 3) {
            wx.navigateTo({
              url: '/page/component/user/project/project'
            })
          } else {
            wx.showModal({
              title: '提示',
              content: '您没有此权限，请联系我们开通！',
              success: function (res) {}
            })
          }
        } else {
          wx.setStorageSync("usertype", "0");
        }
  
      }
    });
  },
  getUserInfo(res) {
    var self = this;
    if (res.detail.userInfo) {
      var user = wx.getStorageSync('user') || {};
      var userInfo = wx.getStorageSync('userInfo') || {};
      if ((!user.openid || (user.expires_in || Date.now()) < (Date.now() + 600)) && (!userInfo.nickName)) {
        wx.login({
          success: function (res) {
            if (res.code) {
              wx.request({
                url: app.globalData.reqUrl + 'wxAuth/callBack?code=' + res.code,
                data: {},
                method: 'GET',  
                success: function (res) {
                  var obj = {};
                  obj.openid = res.data.openid;
                  obj.expires_in = Date(Date.now() + res.data.expires_in);
                  wx.setStorageSync('user', obj);//存储openid
                }
              });
            } else {
              console.log('获取用户登录态失败！' + res.errMsg)
            }
          }
        });
      }
      self.setData({
        viewShowed: "false",
      })
      wx.setStorageSync('userInfo', res.detail.userInfo);
    }else{
      self.setData({
        viewShowed: "false",
      })
    }
  }
})