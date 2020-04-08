// page/component/user/project/datail/datail.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    product:[],
    product2:[],
    project:{},
    date: '',
    index: -1,
    index2: -1,
    address: '',
    flag:true,
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    wx.request({
      url: app.globalData.reqUrl + 'pro/getProduct',
      method: 'post',
      data: {state:1},
      header: { 'content-type': 'application/json' },
      success(res) {
        if(res.data.code=='9000'){
          self.setData({
            product: res.data.name,
            product2: res.data.name
          })
        }
      }
    });
    if(options.id!=undefined){
      wx.setNavigationBarTitle({
        title:"查看项目"
      });
      self.setData({
        flag: false
      });
      wx.request({
        url: app.globalData.reqUrl + 'project/getById',
        method: 'post',
        data: {id:options.id},
        header: { 'content-type': 'application/json' },
        success(res) {
          if(res.data.code=='9000'){
            self.setData({
              project: res.data.project
            })
          }
        }
      })
    }else{
      var user = wx.getStorageSync('userList');
      wx.setNavigationBarTitle({
        title:"新增项目"
      });
      self.setData({
        address: user.hosp,
        flag: true
      })
    }
  },
  /**
   * 产品选择框(修改)
   */
  bindPickerChange: function(e) {
    this.setData({
      index: e.detail.value
    })
  },
  /**
   * 产品选择框(新增)
   */
  bindPickerChange2: function(e) {
    this.setData({
      index2: e.detail.value
    })
  },
  /**
   * 时间选择框
   */
  bindDateChange: function(e) {
    this.setData({
      date: e.detail.value
    })
  },
  //提交新增信息
  formSubmitI: function (e) {
    var that = this;
    var userInfo = wx.getStorageSync('userInfo');
    var user = wx.getStorageSync('user');
    console.log(user.openid)
    if (e.detail.value.name=="" || e.detail.value.pName=="" || e.detail.value.address=="" || e.detail.value.hosp=="" || e.detail.value.day=="") {
      wx.showModal({
        title: '提示',
        content: '请填写完整后提交！',
        success: function (res) {}
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '确认新增项目',
        success: function (res) {
          if (res.confirm) {
            wx.request({
              url: app.globalData.reqUrl + 'project/add',
              method: 'post',
              data: {
                uid: user.openid,
                name: e.detail.value.name,
                pname: e.detail.value.pName,
                address: e.detail.value.address,
                hosp: e.detail.value.hosp,
                state: e.detail.value.desc,
                day: e.detail.value.day
              },
              header: { 'content-type': 'application/json' },
              success(res) {
                  wx.navigateTo({
                    url: '/page/component/user/project/project'
                  })
              }
            })
          }
        }
      })
    }
  },
  //提交修改信息
  formSubmitU: function (e) {
    var that = this;
    var user = wx.getStorageSync('user');
    if (e.detail.value.id=="" || e.detail.value.name=="" || e.detail.value.pName=="" || e.detail.value.address=="" || e.detail.value.hosp=="" || e.detail.value.day=="") {
      wx.showModal({
        title: '提示',
        content: '请填写完整后提交！',
        success: function (res) {}
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '确认修改',
        success: function (res) {
          if (res.confirm) {
            wx.request({
              url: app.globalData.reqUrl + 'project/modify',
              method: 'post',
              data: {
                id: e.detail.value.id,
                uid: user.openid,
                name: e.detail.value.name,
                pname: e.detail.value.pName,
                address: e.detail.value.address,
                hosp: e.detail.value.hosp,
                state: e.detail.value.desc,
                day: e.detail.value.day
              },
              header: { 'content-type': 'application/json' },
              success(res) {
                  wx.navigateTo({
                    url: '/page/component/user/project/project'
                  })
              }
            })
          }
        }
      })
    }
  },
  remove: function (e) {
    wx.showModal({
      title: '提示',
      content: '确认删除！',
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: app.globalData.reqUrl + 'project/remove',
            method: 'post',
            data: {
              id:e.currentTarget.id 
            },
            header: { 'content-type': 'application/json' },
            success(res) {
                wx.navigateTo({
                  url: '/page/component/user/project/project'
                })
            }
          })
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

  }
})