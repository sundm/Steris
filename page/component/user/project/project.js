// page/component/user/project/project.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    projectInfo:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var user = wx.getStorageSync('user');
    var self = this;
    wx.request({
      url: app.globalData.reqUrl + 'project/get',
      method: 'post',
      data: {uid:user.openid},
      header: { 'content-type': 'application/json' },
      success(res) {
        if(res.data.code=='9000'){
          self.setData({
            projectInfo: res.data.listInfo
          })
        }
      }
    })
  },
  /**
   * 详情跳转
   */
  openData: function (options) {
    wx.navigateTo({
      url: '/page/component/user/project/datail/datail?id=' + options.currentTarget.id
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
    wx.reLaunch({
      url: '/page/component/user/user'
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})