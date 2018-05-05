nexus3上传maven仓库到私服:
1、上传全部maven仓库到私服：
新建一个importjar.sh脚本文件，把如下内容全部复制进去
#!/bin/bash
# Get command line params
while getopts ":r:u:p:" opt; do
    case $opt in
        r) REPO_URL="$OPTARG"
        ;;
        u) USERNAME="$OPTARG"
        ;;
        p) PASSWORD="$OPTARG"
        ;;
        esac
done
find . -type f -not -path '*/\.*' -not -path '*/\^archetype\-catalog\.xml*' -not -path '*/\^maven\-metadata\-local*\.xml' -not -path '*/\^maven\-metadata\-deployment*\.xml' -exec curl -u $USERNAME:$PASSWORD -X PUT -v -T {} $REPO_URL{} \;

把脚本文件放到需要上传的maven仓库的根目录然后执行脚本命令：
sh importjar.sh -u admin -p admin123 -r http://128.232.6.23:18081/repository/maven-releases/

参数说明：
importjar.sh   需要执行的脚本文件
admin          登陆私服的用户名
admin123       登陆私服的密码
http://128.232.6.23:18081/repository/maven-releases/    上传到的私服的路径



2、上传单个jar到私服，参考示例：
curl -v -u admin:admin123 --upload-file /home/xbt/repository-xincheng/org/springframework/cloud/spring-cloud-config-server/2.0.0.M9/spring-cloud-config-server-2.0.0.M9.jar http://localhost:19081/repository/maven-releases/org/springframework/cloud/spring-cloud-config-server/2.0.0.M9/spring-cloud-config-server-2.0.0.M9.jar


可能会遇到的问题：
把sh文件变成可执行的脚本文件命令：
*:指的是全部的sh文件

chmod +x   *.sh
Shell脚本报错：-bash: ./switch.sh: /bin/bash^M: bad interpreter: No such file or directory

在给当前shell脚本赋予了执行权限之后，执行报错代码如下
-bash: ./switch.sh: /bin/bash^M: bad interpreter: No such file or directory
主要原因是switch.sh是我在Windows下通过Sublime Text编辑后，在linux系统里执行的。.sh文件的格式为dos格式。而linux只能执行格式为unix格式的脚本。
我们可以通过vi编辑器来查看文件的format格式。步骤如下：

1:首先用vi命令打开当前的shell脚本文件
2:在vi命令模式中使用 :set ff 命令
显示fileformat=doc
解决方法:直接当下输入命令

:set ff=unix
