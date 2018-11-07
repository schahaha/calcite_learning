package cn.schahaha.day01

import java.io.File
import java.sql.{Connection, Driver, DriverManager, PreparedStatement, ResultSet}

/**
  * 使用官方自带的CSVSchema解析csv文件
  */

object HelloWorld {

  def main(args: Array[String]): Unit = {



    Class.forName("org.apache.calcite.jdbc.Driver")


    //1.如果是在windows下操作, json文件里面的路径也需要使用\\作为 分隔符
    //2.table.json文件里面所配置的table所在文件夹路径最好使用绝对路径
    //3.和oracle一样,sql里面的表名和列名会被 转换为大写.所以文件内容也要使用 大写,不然无法匹配上.
    val connection: Connection = DriverManager.getConnection("jdbc:calcite:model=target/classes/day01/table.json")

    val ps: PreparedStatement = connection.prepareStatement("select * from people a join people b on a.age=b.age")

    val rs: ResultSet = ps.executeQuery()

    val count: Int = rs.getMetaData.getColumnCount

    for(x<- 1 to count){
      print(rs.getMetaData.getColumnName(x)+" ")
      if(x==count){
        println()
      }
    }


    while (rs.next()){

      var line=""

      for(x<- 1 to count){


        line=line+" "+rs.getString(x)
      }
      println(line)

    }

    connection.close()









  }

}
