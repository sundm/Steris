// page/component/orders/orders.js
const app = getApp();
Page({
  data:{
    fileInfo:{},
    files:{}
  },
/**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    // 获取文件信息
    wx.request({
      url: app.globalData.reqUrl + 'files/byId',
      method: 'post',
      data: {id:options.id},
      header: { 'content-type': 'application/json' },
      success(res) {
        wx.showLoading({
          title: '加载中',
          mask:true,
        })
        self.downloadFile(res.data.files.url);
        console.log(res.data.files)
        self.setData({
          files: res.data.files
        })
      }
    });
    
    
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    
  },
  
  onShow:function(){
   
  },
  /**
  * 下载文件并预览
  */
 downloadFile: function (name) {
  var self = this;
  console.log(self.data.files);
  let url = app.globalData.reqUrl + name;
  console.log(url);
  var downloadTask = wx.downloadFile({
    url: url,
    header: {},
    success: function (res) {
      var filePath = res.tempFilePath;
      console.log(filePath);
      if (res.statusCode==404) {
        setTimeout(function () {
          wx.hideLoading()
        }, 2000);
        wx.showModal({
          title: '提示',
          content: '加载失败，请重试！',
          success: function (res) {
            
          }
        });
      } else {
        wx.openDocument({
          filePath: filePath,
          success: function (res) {
            setTimeout(function () {
              wx.hideLoading()
            }, 2000);
          },
          fail: function (res) {
            setTimeout(function () {
              wx.hideLoading()
            }, 2000);
          },
          complete: function (res) {
            setTimeout(function () {
              wx.hideLoading()
            }, 2000);
          }
        }) ;
        
      }
    },
    fail: function (res) {
      console.log(res)
      setTimeout(function () {
        wx.hideLoading()
      }, 2000);
      wx.showModal({
        title: '提示',
        content: '网络太差，加载失败，请重试！',
        success: function (res) {
          
        }
      })
    },
    complete: function (res) { },
  });
}
})