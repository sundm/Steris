// page/component/user/file/file.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    fileData:[],
    fileInfo:[],
    showIndex: 0
  },
  /**
* 下载文件并预览
*/
  downloadFile: function (e) {
    console.log(e);
    let type = e.currentTarget.dataset.type;
    let url = e.currentTarget.dataset.url;
    switch (type) {
      case "pdf":
        url += '.pdf';
        break;
      case "word":
        url += '.docx';
        break;
      case "excel":
        url += '.xlsx';
        break;
      default:
        url += '.pptx';
        break;
    }
    wx.downloadFile({
      url: url,
      header: {},
      success: function (res) {
        var filePath = res.tempFilePath;
        console.log(filePath);
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
      },
      fail: function (res) {
        console.log('文件下载失败');
      },
      complete: function (res) { },
    })
  },
  openData: function (e) {
    var self = this;
    console.log(e.currentTarget.id)
    wx.request({
      url: app.globalData.reqUrl + 'files/getFile',
      method: 'post',
      data: {type:e.currentTarget.id,state:'1'},
      header: { 'content-type': 'application/json' },
      success(res) {
        console.log(res.data.fileInfo)
        self.setData({
          fileInfo: res.data.fileInfo
        })
      }
    })
    if (e.currentTarget.dataset.index != this.data.showIndex) {
      self.setData({
        showIndex: e.currentTarget.dataset.index
      })
    } else {
      self.setData({
        showIndex: 0
      })
    }
  },
  openPage: function (e) {
    wx.navigateTo({
      url: '/page/component/user/file/datails/datails?id=' + e.currentTarget.id
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    wx.request({
      url: app.globalData.reqUrl + 'files/getType',
      method: 'post',
      data: {
        type:options.id,
        state:'1'
      },
      header: { 'content-type': 'application/json' },
      success(res) {
        console.log(res.data.typeInfo)
        self.setData({
          fileData: res.data.typeInfo
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
  // onShareAppMessage: function () {

  // }
})