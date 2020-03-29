//获取应用实例
const app = getApp()
Page({
    data: {
      topNum: 0,
      category: [],
      detail:[],
      curIndex: 0,
      isScroll: false,
      toView: 'guowei',
      imgUrl: app.globalData.reqUrl,
      images:{}
  },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
      this.getPro();
      this.getProduct();
    },
    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    scrolltouppe: function () {
      this.getPro();
      this.getProduct();
    },
      onReady(){
          var self = this;
          /* wx.request({
              url:'http://www.gdfengshuo.com/api/wx/cate-detail.txt',
              success(res){
                  self.setData({
                      detail : "res.data"
                  })
              }
          }); */
          
      },
    switchTab(e){
      const self = this;
      this.setData({
        isScroll: true
      })
      setTimeout(function(){
        self.setData({
          toView: e.target.dataset.id,
          curIndex: e.target.dataset.index
        })
      },0)
      setTimeout(function () {
        self.setData({
          isScroll: false
        })
      },1)
    },
    imageLoad: function(e) {
      var $width=e.detail.width,    //获取图片真实宽度
          $height=e.detail.height,
          ratio=$width/$height;    //图片的真实宽高比例
      var viewWidth=337,           //设置图片显示宽度，左右留有16rpx边距
          viewHeight=337/ratio;    //计算的高度值
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
  getPro(){
    var self = this;
      wx.request({
        url: app.globalData.reqUrl + 'pro/getProType',
        method: 'post',
        data: { state: 1 },
        header: { 'content-type': 'application/json' },
        success(res) {
          self.setData({
            category: res.data.proTypes
          })
        }
      })
    },
    getProduct(){
    var self = this;
      wx.request({
        url: app.globalData.reqUrl + 'pro/getProductList',
        method: 'post',
        data: {},
        header: { 'content-type': 'application/json' },
        success(res) {
          self.setData({
            detail: res.data.detail
          })
        }
      })
    },
    openPage: function (e) {
      wx.navigateTo({
        url: '/page/component/category/datails/datails?id=' + e.currentTarget.id
      })
    },
  returnTop: function () {
    this.setData({
      topNum: this.data.topNum = 0
    })
  } 
})