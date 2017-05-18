Eclipse 环境相关的问题
=====================

Q: 新建或打开文件时，默认编码为 `GBK` ，能不能改为 `UTF-8` ？
> A: 在菜单 **Windows** -> **Preferences** 的左上角搜索框中，输入 `encoding` ，接着在 `General` -> `Workspace` 面板 最下方的 `Text file encoding` 里选择 `Other` ，然后将旁边的下拉框值改为 `UTF-8`。

Q: 新建jsp文件时，`charset` 和 `pageEncoding` 默认为 `ISO-8859-1` ，能不能改为 `UTF-8` ？
> A: 在菜单 **Windows** -> **Preferences** 的左上角搜索框中，输入 `encoding` ，接着在 `Web` -> `JSP Files` 面板中，将 `Encoding` 改为 带有 `UTF-8` 的那一项。

Q: 怎么去掉jsp文件第一行的错误 `The superclass "javax.servlet.http.HttpServlet" was not found on the Java Build Path` ？
> A: 图文解答：[百度经验](http://jingyan.baidu.com/article/f79b7cb34f40569144023ef9.html)

Q: 如何添加 **Tomcat** 服务器？
> A: 在Eclipse下面的 **Servers** 面板中，右键 -> `New` -> `Server` ，在弹出的面板中，选择 **Apache** 里面对应的 **Tomcat** 版本，然后在弹出的新面板里指定 **Tomcat** 的安装目录，确定之后再把需要启动的项目添加到右侧即可。

Q: 如何在 **Tomcat** 服务器中添加项目？
> A: 在Eclipse下面的 **Servers** 面板中，右键 -> `Add and Remove...` ，在弹出的面板中，将左侧 `Available` 中的项目添加到右侧 `Configured` 中即可。

Q: 如何启动我的JavaEE项目？
> A: 在Eclipse下面的 **Servers** 面板中，右键 -> `Start` （需要先将项目添加到 `Server` 中），等 `Console` 面板中出现 `信息: Server startup in xxxx ms` 后，访问 http://localhost:8080/项目名/ 即可访问 _/WebContent/_ 目录中的内容。

Q: 如何在 Eclipse 中添加外部的 `jar` 包？
> A: 可以在 `Java Build Path` 中配置，也可以直接拷贝到 _/WebContent/WEB-INF/lib_ 中，Eclipse 会自动识别。

Q: 如果关联 `Java` 源码，以便在代码编辑器中 可以 `Ctrl + 鼠标左键` 点击进入呢？
> A: `Ctrl + 鼠标左键` 进入源码界面，如果没有配置的话，默认会出现 **Class File Editor** 界面。
	此时点击 **Attach Source...** 按钮，在弹出窗口中就可以配置 `Java src` 源文件（即 JDK 安装目录里的 _src.zip_ 文件）了。  
	如果关联的是 `Servlet` 源码，则需先下载 [Tomcat 源码](再按上面的操作配置源码文件)，再按上面的操作配置源码文件即可。  
	如果是第三方的类库，则需要下载第三方类库源码，再按上面的操作配置源码文件（或文件夹）即可。  
	完整操作可以看 [这里](http://jingyan.baidu.com/article/f006222805efddfbd2f0c850.html) ^-^
