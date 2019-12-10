//获取应用实例
const app = getApp()
Page({
  data:{
    casIndex: 0,
    casArray: ['请选择','代理商', '医生'],
    thumb: '',
    nickname: ''
  },
  onLoad: function (){
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
  },
  //提交表单信息
  formSubmit: function (e) {
    var userInfo = wx.getStorageSync('userInfo');
    var user = wx.getStorageSync('user');
    wx.request({
      url: app.globalData.reqUrl + 'user/addUser',
      method: 'post',
      data: {
        userInfo: userInfo,
        openId: user.openid,
        phone: e.detail.value.phone,
        job: e.detail.value.job,
        hosp: e.detail.value.hosp,
        referrer: e.detail.value.people
      },
      header: { 'content-type': 'application/json' },
      success(res) {
        if (res.data.code == "9000") {
          wx.setStorageSync("usertype", "1")
          wx.redirectTo({
            url: '/page/component/msg/msg_success'
          })
        } else {
          wx.redirectTo({
            url: '/page/component/msg/msg_fail'
          })
        }

      }
    })
  },
  /**
 * 生命周期函数--监听页面加载
 */
  bindCasPickerChange: function (e) {
    this.setData({
      casIndex: e.detail.value
    })
  }
});
