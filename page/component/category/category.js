//获取应用实例
const app = getApp()
Page({
    data: {
        category: [
            {name:'设备',id:'shebei'},
			{name:'耗材',id:'haocai'}
        ],
        detail:[
			{
				id:'shebei',
				banner:'/image/img/home1.jpg',
				cate:'设备',
				detail:[
					{thumb:'/image/img/home1.jpg',name:'迈科绝缘检测仪'}
				]
			},
			{id:'haocai',banner:'/image/img/home2.jpg',cate:'耗材'}
		],
        curIndex: 0,
        isScroll: false,
        toView: 'guowei'
  },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
      
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
        
    }
    
})