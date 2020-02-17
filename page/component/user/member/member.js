// page/component/user/member/member.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    thumb: '',
    state: '',
    nickname: '',
    userInfo: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    self.userAuth();
    var usertype = wx.getStorageSync("usertype");
    if(usertype == "1"){
      self.setData({
        state: "审核中"
      })
    } else if(usertype == "2") {
      self.setData({
        state: "正式会员"
      })
    }else{
      self.setData({
        state: "加入会员"
      })
    }
    /**
     * 获取用户信息
     */
    wx.getUserInfo({
      success: function (res) {
        self.setData({
          thumb: res.userInfo.avatarUrl,
          nickname: res.userInfo.nickName
        })
      }
    })
    /**
     * 发起请求获取用户信息
     */
    wx.request({
      url: app.globalData.reqUrl + 'user/getUser',
      method: 'post',
      data: {
        id: 1
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        self.setData({
          userInfo: res.data.userInfo
        })
        console.log(res.data);
      }
    })
  },
  goHome:function(){
    wx.navigateTo({
          url: '/page/component/msg/msg'
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
        if (res.data.code == "9000") {
          wx.setStorageSync("usertype", res.data.state)
          return "1";
        } else {
          wx.setStorageSync("usertype", "0")
        }

      }
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