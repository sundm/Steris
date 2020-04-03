//获取应用实例
const app = getApp()
Page({
  data:{
    casIndex: 0,
    zhiIndex:0,
    casArray: ['请选择', '代理商', '销售人员'],
    zhiArray:[],
    thumb: '',
    viewShowed: "false", //控制授权是否显示
    nickname: ''
  },
  onLoad: function (){
    var user = wx.getStorageSync('user');
    wx.request({
      url: app.globalData.reqUrl + 'user/auth',
      method: 'post',
      data: {
        openId: user.openid
      },
      header: { 'content-type': 'application/json' },
      success(res) {
        if (res.data.code == "9000") {
          var usertype = res.data.state;
          wx.setStorageSync('userList', res.data.userInfo);
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
  getUserInfo(res) {
    var self = this;
    if (res.detail.userInfo) {
      var user = wx.getStorageSync('user') || {};
      var userInfo = wx.getStorageSync('userInfo') || {};
      if ((!user.openid || (user.expires_in || Date.now()) < (Date.now() + 600)) && (!userInfo.nickName)) {
        wx.login({
          success: function (res) {
            if (res.code) {
              wx.request({
                url: app.globalData.reqUrl + 'wxAuth/callBack?code=' + res.code,
                data: {},
                method: 'GET',  
                success: function (res) {
                  var obj = {};
                  obj.openid = res.data.openid;
                  obj.expires_in = Date(Date.now() + res.data.expires_in);
                  wx.setStorageSync('user', obj);//存储openid
                  wx.request({
                    url: app.globalData.reqUrl + 'user/auth',
                    method: 'post',
                    data: {
                      openId: res.data.openid
                    },
                    header: { 'content-type': 'application/json' },
                    success(res) {
                      console.log(res.data);
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
                      }else{
                        wx.showModal({
                          title: '提示',
                          content: '提交异常，请重试！',
                          success: function (res) {}
                        })
                      }
                    }
                  })
                }
              });
            } else {
              console.log('获取用户登录态失败！' + res.errMsg)
            }
          }
        });
      }
      self.setData({
        viewShowed: "false",
      })
      wx.setStorageSync('userInfo', res.detail.userInfo);
    }else{
      self.setData({
        viewShowed: "false",
      })
    }
  },
  /**
   * 发起请求获取用户状态
  */
 userAuth: function () {
  var user = wx.getStorageSync('user');
  wx.request({
    url: app.globalData.reqUrl + 'user/auth',
    method: 'post',
    data: {
      openId: user.openid
    },
    header: { 'content-type': 'application/json' },
    success(res) {
      if (res.data.code == "9000") {
        wx.setStorageSync("usertype", res.data.state);
        wx.setStorageSync("users", res.data.userInfo);
      } else {
        wx.setStorageSync("usertype", "0");
      }

    }
  })
},
  //提交表单信息
  formSubmit: function (e) {
    var that = this;
    var userInfo = wx.getStorageSync('userInfo');
    var user = wx.getStorageSync('user');
    if(user.openid == undefined){
      that.setData({
        viewShowed: "",
      })
      return;
    }
    if (e.detail.value.phone=="" || e.detail.value.job=="请选择" || e.detail.value.hosp=="" || e.detail.value.people=="") {
      wx.showModal({
        title: '提示',
        content: '请填写完整后提交！',
        success: function (res) {}
      })
    } else if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(e.detail.value.phone))){ 
      wx.showModal({
        title: '提示',
        content: '手机号码格式有误，请重填！',
        success: function (res) {}
      });  
      return; 
    } else {
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
          // if (res.data.code == "9000") {
            wx.setStorageSync("usertype", "1")
            wx.redirectTo({
              url: '/page/component/msg/msg_success'
            })
          // } else {
          //   wx.navigateTo({
          //     url: '/page/component/msg/msg_fail'
          //   })
          // }
        }
      })
    }
  },
  goHome:function(){
    wx.switchTab({
      url: '/page/component/index'
    })
  },
  /**
 * 生命周期函数--监听页面加载
 */
  bindCasPickerChange: function (e) {
    this.userAuth();
    this.setData({
      casIndex: e.detail.value
    })
  }
});
