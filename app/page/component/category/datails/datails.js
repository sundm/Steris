// page/component/category/datails/datails.js
//获取应用实例
const app = getApp();
var WxParse = require('../../../../wxParse/wxParse.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods: {},
    num: 1,
    totalNum: 0,
    hasCarts: false,
    curIndex: 0,
    show: false,
    scaleCart: false,
    imgUrl: app.globalData.reqUrl,
    images:{},
    isShow: false, //控制收起展开
    isShow2: false, //控制收起展开
    openDoc:true,
    index:0,
    fileInfo:{},
    files:{},
    progress: ''
  },
  /*
    * isShow做取反操作
    * */
  toChange: function() {
    let that = this;
    that.setData({
        isShow: !that.data.isShow
    })
  },
  unLodding: function() {
    let that = this;
    setTimeout(function () {
      wx.hideLoading()
    }, 100);
    that.setData({isShow2: !that.data.isShow2});
    that.setData({openDoc: false});
  },
  openPage: function (e) {
    var self = this;
     wx.showModal({
      title: '提示',
      content: '确认打开预览！',
      success: function (res) {
        if (res.confirm) {
          // 获取文件信息
          wx.request({
            url: app.globalData.reqUrl + 'files/byId',
            method: 'post',
            data: {id:e.currentTarget.id},
            async:true,
            header: { 'content-type': 'application/json' },
            success(res) {
              wx.showLoading({
                title: '加载中',
              });
              self.setData({
                isShow2: !self.data.isShow2
              });
              self.downloadFile(res.data.files.url);
              self.setData({
                files: res.data.files
              });
            }
          });
        } 
      }
    })
    
    // wx.navigateTo({
    //   url: '/page/component/user/file/datails/datails?id=' + e.currentTarget.id
    // })
  },
  imageLoad: function(e) {
     var $width=e.detail.width,    //获取图片真实宽度
         $height=e.detail.height,
         ratio=$width/$height;    //图片的真实宽高比例
     var viewWidth=550,           //设置图片显示宽度，左右留有16rpx边距
         viewHeight=550/ratio;    //计算的高度值
      var image=this.data.images; 
      //将图片的datadata-index作为image对象的key,然后存储图片的宽高值
      image[e.target.dataset.index]={
         width:viewWidth,
         height:viewHeight
      }
      this.setData({
           images:image
      })
  },
  bindTap(e) {
    const index = parseInt(e.currentTarget.dataset.index);
    this.setData({
      curIndex: index
    })
  },
  /**
   * End
   */

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    wx.request({
      url: app.globalData.reqUrl + 'pro/getPro',
      method: 'post',
      data: {id:options.id},
      header: { 'content-type': 'application/json' },
      success(res) {
        self.setData({
          goods: res.data.product
        });
        var htmlTpl = res.data.product.afterSale;
        WxParse.wxParse('article', 'html', htmlTpl, self, 5);
      }
    });
    wx.request({
      url: app.globalData.reqUrl + 'files/getFile',
      method: 'post',
      data: {type:options.id,state:'1'},
      header: { 'content-type': 'application/json' },
      success(res) {
        self.setData({
          fileInfo: res.data.fileInfo
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
  onShareAppMessage: function () {

  },
  /**
  * 下载文件并预览
  */
 downloadFile: function (name) {
  var self = this;
  self.setData({openDoc: true});
  let url = app.globalData.reqUrl + name;
  const DownloadTask = wx.downloadFile({
    url: url,
    header: {},
    success: function (res) {
      var filePath = res.tempFilePath;
      if (res.statusCode==404) {
        wx.showModal({
          title: '提示',
          content: '加载失败，请重试！',
          success: function (res) {}
        });
      } else {
        if(self.data.openDoc){
          console.log(filePath);
          wx.openDocument({
            filePath: filePath,
            success: function (res) {
              self.setData({openDoc: true});
            },
            fail: function (res) {},
            complete: function (res) {}
          });
        } 
      }
    },
    fail: function (res) {
      wx.showModal({
        title: '提示',
        content: '网络太差，加载超时，请重试！',
        success: function (res) {
          
        }
      })
    },
    complete: function (res) { 
      self.setData({isShow2: false});
      setTimeout(function () {wx.hideLoading()}, 100);
    },
  });
}
})