---
title: js控制禁止微信分享
---

- #### 禁止分享


```
if (typeof WeixinJSBridge == "undefined"){
    if( document.addEventListener ){
        document.addEventListener("WeixinJSBridgeReady", onBridgeReady, false);
    }else if (document.attachEvent){
        document.attachEvent("WeixinJSBridgeReady", onBridgeReady);
        document.attachEvent("onWeixinJSBridgeReady", onBridgeReady);
    }
}else{
     onBridgeReady();
}
```

```
function onBridgeReady(){
    WeixinJSBridge.call("hideOptionMenu");
 }
```

<!-- more -->

- ### 打开分享

> WeixinJSBridge.call("hideOptionMenu");
替换成WeixinJSBridge.call('showOptionMenu');

```
function onBridgeReady(){
    WeixinJSBridge.call('showOptionMenu');
 }
```