

- #### 下载sourcetree

> 根据自己系统下载对应的安装程序并安装，本例以windows为例
> https://www.sourcetreeapp.com/

![image](https://upload-images.jianshu.io/upload_images/7030886-96db6a092d1b352e.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)



- #### github创建新仓库

进到自己github新建仓库，按如下步骤操作即可

![image](https://upload-images.jianshu.io/upload_images/7030886-0cfcdb1f6684cb49.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

![image](https://upload-images.jianshu.io/upload_images/7030886-ecca8d60af24e8a5.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/522)

![image](https://upload-images.jianshu.io/upload_images/7030886-3e01c77ef37feffc.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

![image](https://upload-images.jianshu.io/upload_images/7030886-a08f4947c94c0ad8.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

至此新仓库创建完成

- #### 下载文件到本地仓库

按照如下步骤下载远程仓库到本地

![image](https://upload-images.jianshu.io/upload_images/7030886-77025ccbd5ff1dd3.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

点击需要clone到本地的仓库后面的“clone”

![image](https://upload-images.jianshu.io/upload_images/7030886-bed56cab8c09767f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

图中所示是默认下载到本地的仓库地址，可以点击后面的“浏览”进行修改本地仓库地址。然后点击“克隆”按钮

![image](https://upload-images.jianshu.io/upload_images/7030886-868147f7749483ab.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

克隆完成界面显示如上图所示，本地仓库文件所下图

![image](https://upload-images.jianshu.io/upload_images/7030886-8108902c8f570c51.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/511)

.git文件是sourcetree生成的，可以忽略，README.md文件是从远程仓库下载下来的

- #### 上传文件到远程仓库

修改README.md文件，添加一行内容“>在本地添加这行文字”

新建一个文本文件“new.txt”

可以看到有改动的文件，点击“暂存所有”将改动或新添加的文件放到暂存区

![image](https://upload-images.jianshu.io/upload_images/7030886-a142330866cbf426.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

可以将放到暂存区的文件取消放到暂存区，需要提交的文件都要先放到暂存区，点击提交按钮

![image](https://upload-images.jianshu.io/upload_images/7030886-771d00cdba08e4bb.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

填写提交注释(写注释是个好习惯哦！)，点击“提交”按钮将文件提交到本地仓库

![image](https://upload-images.jianshu.io/upload_images/7030886-64b8c5286549ce20.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

如下图是正在提交文件到本地仓库 

![image](https://upload-images.jianshu.io/upload_images/7030886-f1b83a010256da9a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

此时已经将暂存区的文件提交到了本地仓库，此时还没有到远程仓库，如下图所示。点击“推送”按钮，将文件推送到远程仓库。

![image](https://upload-images.jianshu.io/upload_images/7030886-cd1c99c9ae35b9e3.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

选择推送到的远程分支，选择你要推送到的远程分支，示例是推送到master，然后点击“推送”按钮

![image](https://upload-images.jianshu.io/upload_images/7030886-345d2a385f172490.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

下图所示正在推送到远程仓库

![image](https://upload-images.jianshu.io/upload_images/7030886-fd87e1fc410dd695.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

下图所示推送到远程仓库完成

![image](https://upload-images.jianshu.io/upload_images/7030886-81c846293128a75a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

打开github远程仓库，到新建的仓库里面，可以看到README.md文件是已经修改之后的，新建的new.txt文件也已经存在。

![image](https://upload-images.jianshu.io/upload_images/7030886-b4d244778678b53e.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

