package cn.schahaha.day01

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}

object ExcelTest {

  def main(args: Array[String]): Unit = {

    Class.forName("org.apache.calcite.jdbc.Driver")

    val connection: Connection = DriverManager.getConnection("jdbc:calcite:model=target/classes/day01/excel.json")

    val ps: PreparedStatement = connection.prepareStatement("select * from PEOPLE   limit 2")
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
