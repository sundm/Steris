// page/component/user/member/member.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    state:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var users = wx.getStorageSync('users');
    if(users.state==1){
      this.setData({
        state: "待审核"
      })
    } else if (users.state == 2){
      this.setData({
        state: "正式会员"
      })
    }
    this.setData({
      userInfo: users
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