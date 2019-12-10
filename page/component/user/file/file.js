// page/component/user/file/file.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    fileData:[
      {
        type:'Excel',
        data: [
          { downUrl: '', name: '日期表', imgUrl: '/image/home/excel.png' },
          { downUrl: '', name: '日期表2', imgUrl: '/image/home/excel.png' },
          { downUrl: '', name: '日期表2', imgUrl: '/image/home/excel.png' }
        ]
      },
      {
        type: 'Word',
        data: [
          { downUrl: '', name: 'Word文件', imgUrl: '/image/home/word.png' },
          { downUrl: '', name: 'Word文件', imgUrl: '/image/home/word.png' },
          { downUrl: '', name: 'Word文件', imgUrl: '/image/home/word.png' }
        ]
      },
      {
        type: 'PPT',
        data: [
          { downUrl: '', name: 'PPT文件', imgUrl: '/image/home/ppt.png' },
          { downUrl: '', name: 'PPT文件', imgUrl: '/image/home/ppt.png' },
          { downUrl: '', name: 'PPT文件', imgUrl: '/image/home/ppt.png' }
        ]
      },
      {
        type: 'PDF',
        data: [
          { downUrl: '', name: 'PDF文件', imgUrl: '/image/home/pdf.png' },
          { downUrl: '', name: 'PDF文件', imgUrl: '/image/home/pdf.png' },
          { downUrl: '', name: 'PDF文件', imgUrl: '/image/home/pdf.png' }
        ]
      }
    ],
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
    if (e.currentTarget.dataset.index != this.data.showIndex) {
      this.setData({
        showIndex: e.currentTarget.dataset.index
      })
    } else {
      this.setData({
        showIndex: 0
      })
    }
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
   * 用户点击右上角分享
   */
  // onShareAppMessage: function () {

  // }
})