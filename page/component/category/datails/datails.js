// page/component/category/datails/datails.js
//获取应用实例
const app = getApp();
var WxParse = require('../../../../wxParse/wxParse.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods: {
      id: 1,
      image: '/image/img/home1.jpg',
      title: '迈科绝缘检测仪',
      price: '####',
      stock: '有货',
      detail: '这里是迈科绝缘检测仪详情。',
      parameter: '规格：######',
      service: '终身保修'
    },
    num: 1,
    totalNum: 0,
    hasCarts: false,
    curIndex: 0,
    show: false,
    scaleCart: false,
    imgUrl: app.globalData.reqUrl
  },
  bindTap(e) {
    const index = parseInt(e.currentTarget.dataset.index);
    this.setData({
      curIndex: index
    })
  },
  /**
   * End
   */

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    console.log(options.id);
    wx.request({
      url: app.globalData.reqUrl + 'pro/getPro',
      method: 'post',
      data: {id:options.id},
      header: { 'content-type': 'application/json' },
      success(res) {
        console.log(res.data.product.afterSale)
        var htmlTpl = res.data.product.afterSale;
        WxParse.wxParse('article', 'html', htmlTpl, self, 5);
        self.setData({
          goods: res.data.product
        })
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