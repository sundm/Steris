// page/component/new-pages/user/user.js
Page({
  data:{
    thumb:'',
    nickname:''
  },
  openMsg: function () {
    wx.navigateTo({
      url: '../msg/msg'
    })
  },
  onLoad(){
    this.getUser();
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.getUser();
    wx.stopPullDownRefresh();  //停止下拉刷新
  },
  onShow(){
    var self = this;
    /**
     * 获取本地缓存 地址信息
     */
    wx.getStorage({
      key: 'address',
      success: function(res){
        self.setData({
          hasAddress: true,
          address: res.data
        })
      }
    })
  },
  getUser(){
    var self = this;
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
  }
})