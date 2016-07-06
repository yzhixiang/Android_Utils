# Android开发中常用的代码模块的积累
---

在Android开发中有很多代码模块是重复的，因此可以将其积累起来，成为一个代码仓库，这样会为将来的开发节省很多时间。

> 下面列出一些我使用过的代码模块，因为想将每个模块的功能描述清楚，所以每个文件代码量比较少，实际开发时可以将多个类似功能的模块整合到一起。

工具类名 | 简单描述 
---------------- | ------------- 
[ScreenUtil](categories/ScreenUtil.java) | 获取屏幕相关属性。
[ShowDialogForSelectPic](categories/ShowDialogForSelectPic.java) | 显示选择系统图库 相机对话框。 
[StreamTools](categories/StreamTools.java) | 将输入的InputStream流转化成字符串返回
[SmsTools](categories/SmsTools.java) | 备份短信的工具类，有三个参数：1短信备份后文件的路径；2进度条；3进度条对话框。工具类最后面给出了一个使用示例。
