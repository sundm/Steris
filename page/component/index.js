//获取应用实例
const app = getApp()

Page({
  data: {
    imgUrls: [
      '/image/tu2.jpg',
      '/image/tu2.jpg',
      '/image/tu2.jpg'
    ],
    indicatorDots: false,
    autoplay: false,
    interval: 3000,
    duration: 800,
    headInfo:[]
  },
  onLoad: function (options) {
    this.getNews()
    this.userAuth()
    var usertype = wx.getStorageSync("usertype");
    console.log("获取到userType：" + usertype)
    var user = wx.getStorageSync('user');
    var userInfo = wx.getStorageSync('userInfo');
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.getNews();
    this.userAuth();
  },
  /**
   * 判断是否登录
   */
  navClick: function (options) {
    this.userAuth();
    var usertype = wx.getStorageSync("usertype");
    if (usertype == "1") {
      wx.navigateTo({
        url: '/page/component/msg/msg_success'
      })
    } else if (usertype == "2") {
      wx.navigateTo({
        url: '/page/component/user/news/news'
      })
    } else {
      wx.navigateTo({
        url: '/page/component/msg/msg'
      })
    }
  },
  /**
   * 获取活动信息
   */
  getNews: function (){
    var self = this;
    wx.request({
      url: app.globalData.reqUrl + 'news/getHead',
      method: 'post',
      data: {},
      header: { 'content-type': 'application/json' },
      success(res) {
        self.setData({
          headInfo: res.data.headInfo
        })
      }
    })
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
        if (res.data.code=="9000"){
          wx.setStorageSync("usertype", res.data.state)
        }else{
          wx.setStorageSync("usertype", "0")
        }
        
      }
    })
  }
})