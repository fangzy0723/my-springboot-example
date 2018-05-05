1、java.net.SocketException: Too many open files问题分析及解决方案
用ulimit -a看看linux打开文件限制
执行ulimit -a  命令查看open files的最大数 
用lsof -p [进程ID] 可以看到某ID的打开文件状况。进程ID可能用 ps -ef|grep java列出  

ulimit -n 4096 临时把打开文件数的上限设为了4096
如果要永久生效，可以通过下面的方式设置。
修改/etc/security/limits.conf 添加如下一行：
* - nofile 1006154
* 表示任意用户
