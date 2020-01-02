// page/component/test/test.js
var WxParse = require('../../../wxParse/wxParse.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var htmlTpl = '<p style="text-align:center"><img src="http://localhost:8089/image/20191230/1577678552039017391.jpg" title="1577678552039017391.jpg" alt="6nyswe5z6tu3.jpg" width="622" height="404"/></p><h3 style="text-align: center;">问问企鹅我去完成</h3><hr/><p><span style="text-decoration: underline;"><em><strong>单位为打完等我群</strong></em></span><br/></p>';
    console.log(htmlTpl)
    WxParse.wxParse('article', 'html', htmlTpl, that, 5);
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