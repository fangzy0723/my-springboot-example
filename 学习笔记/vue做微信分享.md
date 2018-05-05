---
title: vue做微信分享
---

- #### 下载weixin-js-sdk 的依赖

```
    cnpm install weixin-js-sdk --save
```

- #### 在需要分享的文件中引入

```
    import wx from 'weixin-js-sdk'
```

- #### 分享及相关的前端代码

<!-- more -->

```
        mounted:function () {
            let urlStr = location.href.split('#')[0];
            const ua = window.navigator.userAgent.toLowerCase();
            if(ua.match(/MicroMessenger/i) == 'micromessenger'){
				//获取签名串
                this.$http.post('/wechat/getSignature',
                    {"urlStr":urlStr}
                ).then((res) => {
					// 请求成功的回调
                    var result = res.data;
                if(result.code==1){
                    let data = JSON.parse(result.data);
                    wx.config({
                        debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                        appId : data.appId, // 必填，公众号的唯一标识
                        timestamp : data.timestamp, // 必填，生成签名的时间戳
                        nonceStr : data.nonceStr, // 必填，生成签名的随机串
                        signature : data.signature,// 必填，签名，见附录1
                        jsApiList: [  //必填，需要使用的JS接口列表
                            'onMenuShareTimeline',
                            'onMenuShareAppMessage',
                            'hideMenuItems',
                            'showMenuItems'
                        ]
                    });
                    let title = "分享的标题";
                    let desc = "分享的副标题";
                    let link = "分享的链接，域名要跟微信公众号配置的安全域名一致";
                    let imgUrl = "分享图片的url";
                    wx.ready(function() {
                    //批量显示菜单项
                        wx.showMenuItems({
                            menuList: [
                                "menuItem:share:appMessage"
                                "menuItem:share:timeline"
                            ], // 要显示的菜单项
                            success: function (res) {},
                            fail: function (res) {}
                        });
						// 批量隐藏菜单项
                        wx.hideMenuItems({
                            menuList: [
                                'menuItem:share:qq', //分享到QQ
                                'menuItem:share:weiboApp', // 分享到Weibo
                                'menuItem:favorite', // 收藏
                                'menuItem:share:QZone', // 分享到 QQ 空间
                                'menuItem:copyUrl', // 复制链接
                                'menuItem:openWithQQBrowser', // 在QQ浏览器中打开
                                'menuItem:openWithSafari', // 在Safari中打开
                                'menuItem:share:email', // 邮件
                                'menuItem:readMode', // 阅读模式
                                'menuItem:originPage' // 原网页
                            ] ,
                            success: function (res) {},
                            fail: function (res) {}
                        });
						//分享给朋友
                        wx.onMenuShareAppMessage({
                            title : title,// 分享标题
                            desc : desc,// 分享描述
                            link : link,// 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl : imgUrl, // 分享图标
                            type : 'link', // 分享类型,music、video或link，不填默认为link
                            dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
                            success : function() {},
                            cancel : function() {}
                        });
						//分享到朋友圈
                        wx.onMenuShareTimeline({
                            title : title,// 分享标题
                            link : link,// 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl : imgUrl, // 分享图标
                            success : function() {},
                            cancel : function() {}
                        });
                    });
                    wx.error(function(res) {
						// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                        alert("error==" + res.errMsg);
                    });
                }else{
                    wx.hideOptionMenu();
                }
            	},  (err) => {
					// 请求失败的回调
                    console.log("获取签名串出错！"+err);
               	 }
            	);
        	}
        }
```
- #### 后端获取签名串主要参考代码

```
	public String getSignature(String urlStr) { 
		Map modelMap = new HashMap();
		String timestamp = System.currentTimeMillis()/1000+"";//时间戳  单位是秒
		String nonceStr = "";//随机字符串
		String jsapi_ticket = null;//调用接口获取
		try {
			jsapi_ticket = "请求微信服务器获取jsapi_ticket，可参考api";
			String signValue = "jsapi_ticket="+jsapi_ticket+"&noncestr="+nonceStr+"×tamp="+timestamp+"&url="+urlStr;
			String signature = getSha1(signValue);
			modelMap.put("timestamp", timestamp);//生成签名的时间戳
			modelMap.put("nonceStr", nonceStr);//生成签名的随机串
			modelMap.put("appId","APPID");//公众号的唯一标识
			modelMap.put("signature",signature );//签名串
			return StringUtil.toJson(modelMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
```
```
	/**
	 * 对密钥进行sha1加密
	 */
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("GBK"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

```