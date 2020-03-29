// page/component/new-pages/user/user.js
//获取应用实例
const app = getApp()
Page({
  data:{
    thumb:'',
    nickname:'',
    typeInfo:[]
  },
  openMsg: function () {
    wx.navigateTo({
      url: '../msg/msg'
    })
  },
  onLoad(){
    var self = this;
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
  navClick(e){
    var self = this;
    var user = wx.getStorageSync('user');
    wx.request({
      url: app.globalData.reqUrl + 'user/auth',
      method: 'post',
      data: {openId: user.openid},
      header: { 'content-type': 'application/json' },
      success(res) {
        if (res.data.code == "9000") {
          wx.setStorageSync("usertype", res.data.state);
          wx.setStorageSync("users", res.data.userInfo);
          if (res.data.userInfo.rId == 3) {
            wx.navigateTo({
              url: '/page/component/user/project/project'
            })
          } else {
            wx.showModal({
              title: '提示',
              content: '您没有此权限，请联系我们开通！',
              success: function (res) {}
            })
          }
        } else {
          wx.setStorageSync("usertype", "0");
        }
  
      }
    });
    
  }
})