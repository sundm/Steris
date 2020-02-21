//获取应用实例
const app = getApp()

Page({
  data: {
    imgUrls: [],
    indicatorDots: false,
    autoplay: false,
    interval: 3000,
    duration: 800,
    headInfo: [],
    imgUrl: app.globalData.reqUrl,
    images:{}
  },
  onLoad: function (options) {
    this.getNews();
    this.getHome();
    this.userAuth();
    var usertype = wx.getStorageSync("usertype");
    console.log("获取到userType：" + usertype);
    var user = wx.getStorageSync('user');
    var userInfo = wx.getStorageSync('userInfo');
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.getNews();
    this.userAuth();
    wx.stopPullDownRefresh();  //停止下拉刷新
  },
  /**
   * 判断是否登录
   */
  navClick: function (options) {
    this.userAuth();
    var usertype = wx.getStorageSync("usertype");
    var hId = options.currentTarget.id;
    // if (usertype == "1") {
    //   wx.navigateTo({
    //     url: '/page/component/msg/msg_success'
    //   })
    // } else if (usertype == "2") {
      wx.navigateTo({
        url: '/page/component/user/news/news?hId=' + hId
      })
    // } else {
    //   wx.navigateTo({
    //     url: '/page/component/msg/msg'
    //   })
    // }
  },
  /**
   * 大图跳转
   */
  goPro: function (options) {
    wx.navigateTo({
      url: '/page/component/category/datails/datails?id=' + options.currentTarget.id
    })
  },
  imageLoad: function(e) {
     var $width=e.detail.width,    //获取图片真实宽度
         $height=e.detail.height,
         ratio=$width/$height;    //图片的真实宽高比例
     var viewWidth=750,           //设置图片显示宽度，左右留有16rpx边距
         viewHeight=750/ratio;    //计算的高度值
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
  /**
   * 获取活动信息
   */
  getNews: function (){
    var self = this;
    wx.request({
      url: app.globalData.reqUrl + 'news/getHead',
      method: 'post',
      data: {state:'1'},
      header: { 'content-type': 'application/json' },
      success(res) {
        console.log(res.data.headInfo)
        self.setData({
          headInfo: res.data.headInfo
        })
      }
    })
  },
  /**
   * 获取大图信息
   */
  getHome: function (){
    var self = this;
    wx.request({
      url: app.globalData.reqUrl + 'news/getHome',
      method: 'post',
      data: {},
      header: { 'content-type': 'application/json' },
      success(res) {
        console.log(res.data.homes)
        self.setData({
          imgUrls: res.data.homes
        })
      }
    })
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
  }
})