// page/component/user/vip/vip.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    vipCard:[
      {
        title:'尊享会员',
        url:'/image/home/index/canpinhuihuiyuanv1.png',
        text:'下载数量20次生命周期函数--监听页面加载生命周期函数--监听页面加载生命周期函数--监听页面加载生命周期函数--监听页面加载生命周期函数--监听页面加载生命周期函数--监听页面加载'
      }
    ],
    showIndex: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
   * 展开会员信息
   */
  navClick: function (e) {
    if (e.currentTarget.dataset.index != this.data.showIndex) {
      this.setData({
        showIndex: e.currentTarget.dataset.index
      })
    } else {
      this.setData({
        showIndex: 0
      })
    }
  }
})