// page/component/auth/auth.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  getUserInfo(res) {
    if (res.detail.userInfo) {
      var that = app
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
                  console.log(res.data.openid)
                }
              });
            } else {
              console.log('获取用户登录态失败！' + res.errMsg)
            }
          }
        });
      }
      this.userAuth();
      wx.setStorageSync('userInfo', res.detail.userInfo),
      wx.redirectTo({
        url: '/page/component/msg/msg'
      })
    } else {
      wx.redirectTo({
        url: '/page/component/auth/auth-no'
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var userInfo = wx.getStorageSync('userInfo');
    if (userInfo){
      wx.switchTab({
        url: '/page/component/index'
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

  },
  /**
   * 发起请求获取用户状态
  */
  userAuth: function () {
    var user = wx.getStorageSync('user');
    wx.request({
      url: app.globalData.reqUrl + 'user/auth',
      method: 'post',
      data: {
        openId: user.openid
      },
      header: { 'content-type': 'application/json' },
      success(res) {
        if (res.data.code == "9000") {
          wx.setStorageSync("usertype", res.data.state)
        } else {
          wx.setStorageSync("usertype", "0")
        }

      }
    })
  }
})