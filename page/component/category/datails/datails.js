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
    index:0,
    fileInfo:{},
    files:{}
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
  openPage: function (e) {
     wx.showModal({
      title: '提示',
      content: '确认打开预览！',
      success: function (res) {
        if (res.confirm) {
          wx.navigateTo({
            url: '/page/component/orders/orders?id=' + e.currentTarget.id
          })
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
        console.log(res.data.product.afterSale)
        var htmlTpl = res.data.product.afterSale;
        WxParse.wxParse('article', 'html', htmlTpl, self, 5);
        self.setData({
          goods: res.data.product
        })
      }
    });
    wx.request({
      url: app.globalData.reqUrl + 'files/getFile',
      method: 'post',
      data: {type:options.id,state:'1'},
      header: { 'content-type': 'application/json' },
      success(res) {
        console.log(res.data.fileInfo)
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
 downloadFile: function (e) {
 
  var self = this;
  let url = app.globalData.reqUrl +this.data.files.url;
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
          content: '加载失败，请重试1！',
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
        content: '加载失败，请重试2！',
        success: function (res) {
          
        }
      })
    },
    complete: function (res) { },
  });
}
})