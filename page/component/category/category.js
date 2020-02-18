//获取应用实例
const app = getApp()
Page({
    data: {
      topNum: 0,
      category: [],
      detail:[
			{
            id: 's3',
            banner: '/image/img/home1.jpg',
            cate: '设备类',
            detail: [
              { thumb: '/image/img/home1.jpg', name: '迈科绝缘检测仪', url: 'datails/datails' },
              { thumb: '/image/img/home2.jpg', name: '迈科MM513手持式绝缘检测仪', url: '/' },
              { thumb: '/image/img/home3.jpg', name: '赢祥气体监测仪', url: '/' }
            ]
			},
			{
        id: 's4', banner: '/image/img/home2.jpg', cate: '耗材类',
        detail: [
          { thumb: '/image/img/home7.png', name: '微科器械预处理凝胶', url: '/' },
          { thumb: '/image/img/home8.png', name: '微科酶清洗剂', url: '/' },
          { thumb: '/image/img/home9.png', name: '微科碱性清洗剂', url: '/' },
          { thumb: '/image/img/home10.png', name: '微科器械浓缩润滑剂', url: '/' },
          { thumb: '/image/img/home11.png', name: '微科浓缩器械除锈剂', url: '/' },
          { thumb: '/image/img/home6.png', name: '微科除锈湿巾', url: '/' },
          { thumb: '/image/img/home5.png', name: '微科残胶去除剂', url: '/' }
        ]
      },
      {
        id: 's5', banner: '/image/img/home2.jpg', cate: '信息类',
        detail: [
          { thumb: '/image/img/home4.png', name: '消毒供应室信息化系统', url: '/' }
        ]
      }
		],
        curIndex: 0,
        isScroll: false,
        toView: 'guowei',
        imgUrl: app.globalData.reqUrl
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
  getPro(){
    var self = this;
      wx.request({
        url: app.globalData.reqUrl + 'pro/getProType',
        method: 'post',
        data: { state: 1 },
        header: { 'content-type': 'application/json' },
        success(res) {
          console.log(res.data.proTypes)
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
          console.log(res.data.detail)
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