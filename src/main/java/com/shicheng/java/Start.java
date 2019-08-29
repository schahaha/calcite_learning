package com.shicheng.java;

import java.sql.*;

public class Start {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.apache.calcite.jdbc.Driver");


        //1.如果是在windows下操作, json文件里面的路径也需要使用\\作为 分隔符
        //2.table.json文件里面所配置的table所在文件夹路径最好使用绝对路径
        //3.和oracle一样,sql里面的表名和列名会被 转换为大写.所以文件内容也要使用 大写,不然无法匹配上.
       Connection connection= DriverManager.getConnection("jdbc:calcite:model=target/classes/day01/excel.json");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from table1 a join table1 b on a.sex=b.sex   ");

        ResultSet rs = preparedStatement.executeQuery();

        int count = rs.getMetaData().getColumnCount();

        while (rs.next()){

            StringBuilder line=new StringBuilder("");
            for(int i=0;i<count;i++){

                line.append(rs.getString(i+1)).append("   ");
            }
            System.out.println(line);

        }


    }
}
