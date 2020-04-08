App({
  onLaunch: function () {
    wx.setStorageSync('status', true),
      wx.setStorageSync("usertype", "0"),
    console.log('App Launch')
    var user = wx.getStorageSync('user');
    wx.request({
      url: this.globalData.reqUrl + 'user/auth',
      method: 'post',
      data: {
        openId: user.openid
      },
      header: { 'content-type': 'application/json' },
      success(res) {
        if (res.data.code == "9000") {
          var usertype = res.data.state;
          if (usertype == "1") {
            wx.redirectTo({
              url: '/page/component/msg/msg_success'
            })
          } else if (usertype == "2") {
            wx.switchTab({
              url: '/page/component/index'
            })
          }
        }

      }
    })
  },
  onShow: function () {
    // if(this.globalData.hasLogin==false){
    //   wx.reLaunch({
    //     url: 'page/component/msg/msg'
    //   })
    // }
    console.log('App Show')
  },
  onHide: function () {
    console.log('App Hide')
  },
  userAuth:function(op){
    const userInfo = wx.getStorageSync('userInfo');
    /**
     * 发起请求获取活动信息
     */
    wx.request({
      url: app.globalData.reqUrl + 'auth/auth',
      method: 'post',
      data: {
        openId: userInfo.openid
      },
      header: { 'content-type': 'application/json' },
      success(res) {
        console.log(res.data)
      }
    })
  },
  globalData: {
    hasLogin: false,
    reUrl:"http://localhost:8089/",
    reqUrl:"https://w.bjwinstech.com:8089/"
  }
})
