//获取应用实例
const app = getApp()
// page/component/user/news/news.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title:"新品发布会",
    time:"2019-11-19",
    imgUrl:"/image/home/huodong.png",
    text:"下载注意：效果图仅展示用，下载包含源文件但无效果图及附属素材。使用之前请 仔细检查文字及其他文件内容，文件问题可申请退款，但网站不对因此造成的制作 费用等其他损失负责，如有疑问请先联系网站客服进行了解。",
    newsInfo:[],
    headInfo: {},
    imgType:"1",
    textType: "2",
    imgUrl: app.globalData.reqUrl
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    /**
     * 发起请求获取活动信息
     */
    wx.request({
      url: app.globalData.reqUrl + 'news/getNews',
      method: 'post',
      data: { headId: options.hId},
      header: { 'content-type': 'application/json' },
      success(res) {
        console.log(res.data.newsInfo)
        self.setData({
          newsInfo: res.data.newsInfo,
          headInfo: res.data.head
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