### 【WordCount】是一文件信息统计程序

- java实现

- 运行模式：wc.exe [parameter] [file_name]

- 基本功能列表：

  wc.exe -c file.c     //返回文件 file.c 的字符数

  wc.exe -w file.c    //返回文件 file.c 的词的数目  

  wc.exe -l file.c      //返回文件 file.c 的行数

- 扩展功能：

  ​	 -a   返回更复杂的数据（代码行 / 空行 / 注释行）。  

  ​	 -s   递归处理目录下符合条件的文件。wc.exe -s [parameter] [file_name]

  （例：wc.exe -s -a . .txt返回当前目录及子目录中所有*.txt 文件的代码行数、空行数、注释行数。）

  