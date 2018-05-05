把sh文件变成可执行的脚本文件命令：    
*:指的是全部的sh文件
```
chmod +x   *.sh
```

Shell脚本报错：-bash: ./switch.sh: /bin/bash^M: bad interpreter: No such file or directory

> 在给当前shell脚本赋予了执行权限之后，执行报错代码如下


```
-bash: ./switch.sh: /bin/bash^M: bad interpreter: No such file or directory
```
> 主要原因是switch.sh是我在Windows下通过Sublime Text编辑后，在linux系统里执行的。.sh文件的格式为dos格式。而linux只能执行格式为unix格式的脚本。

我们可以通过vi编辑器来查看文件的format格式。步骤如下：

> 1:首先用vi命令打开当前的shell脚本文件   
> 2:在vi命令模式中使用 :set ff 命令   
> 显示fileformat=doc

解决方法:直接当下输入命令
```
:set ff=unix
```
