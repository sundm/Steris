// page/component/user/file/datails/datails.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    files:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    wx.request({
      url: app.globalData.reqUrl + 'files/byId',
      method: 'post',
      data: {id:options.id},
      header: { 'content-type': 'application/json' },
      success(res) {
        console.log(res.data.fileInfo)
        self.setData({
          files: res.data.files
        })
      }
    })
  },
  getDown:function(e){
    var self = this;
    this.userAuth();
    var usertype = wx.getStorageSync("usertype");
    if (usertype == "1") {
      wx.showModal({
        title: '提示',
        content: '会员申请审核中，请通过后重试！',
        success: function (res) {}
      })
    } else if (usertype == "2") {
      wx.showModal({
        title: '提示',
        content: '确认下载！',
        success: function (res) {
          if (res.confirm) {
            self.downloadFile();
          } 
        }
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '需要会员下载，是否加入会员！',
        success: function (res) {
          if (res.confirm) {
            wx.navigateTo({
              url: '/page/component/msg/msg'
            })
          } 
        }
      })
    }
  },
  /**
   * 判断是否登录
   */
  navClick: function (options) {
    this.userAuth();
    var usertype = wx.getStorageSync("usertype");
    if (usertype == "1") {
      wx.navigateTo({
        url: '/page/component/msg/msg_success'
      })
    } else if (usertype == "2") {
      this.downloadFile();
    } else {
      wx.navigateTo({
        url: '/page/component/msg/msg'
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
        if (res.data.code=="9000"){
          wx.setStorageSync("usertype", res.data.state)
          wx.setStorageSync("users", res.data.userInfo)
        }else{
          wx.setStorageSync("usertype", "0")
        }
        
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

  },
  
  /**
  * 下载文件并预览
  */
    downloadFile: function (e) {
      console.log(e);
      var self = this;
      let url = app.globalData.reqUrl +this.data.files.url;
      var downloadTask = wx.downloadFile({
        url: url,
        header: {},
        success: function (res) {
          var filePath = res.tempFilePath;
          console.log(res);
          if (res.statusCode==404) {
            wx.showModal({
              title: '提示',
              content: '下载失败',
              success: function (res) {}
            })
          } else {
            wx.showModal({
              title: '提示',
              content: '下载成功，是否打开预览！',
              success: function (res) {
                if(res.confirm){
                  wx.openDocument({
                    filePath: filePath,
                    success: function (res) {
                      console.log('打开文档成功')
                    },
                    fail: function (res) {
                      console.log(res);
                      console.log(url);
                    },
                    complete: function (res) {
                      console.log(res);
                    }
                  })
                }
              }
            })
             
          }
        },
        fail: function (res) {
          wx.showModal({
            title: '提示',
            content: '下载失败',
            success: function (res) {
              
            }
          })
        },
        complete: function (res) { },
      });
      downloadTask.onProgressUpdate((res) => {
        if (res.progress === 100) {
          this.setData({
            progress: ''
          });
        } else {
          this.setData({
            progress: res.progress + '%'
          });
        }
      });
    }
})