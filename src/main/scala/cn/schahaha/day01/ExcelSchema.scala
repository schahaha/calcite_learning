package cn.schahaha.day01

import java.io.File
import java.util

import com.google.common.collect.{ImmutableMap, Multimap}
import org.apache.calcite.linq4j.tree.Expression
import org.apache.calcite.rel.`type`.{RelDataType, RelDataTypeFactory}
import org.apache.calcite.schema
import org.apache.calcite.schema.{Schema, SchemaPlus, Statistic, Table}
import org.apache.calcite.schema.impl.AbstractSchema

/**
  * 自定义一种用于读取excel的schema
  */

class ExcelSchema(val file:File) extends AbstractSchema{



  /**
    * 遍历文件, 将tableName和file对应在一个map中
    * @return
    */
  override def getTableMap: util.Map[String, Table] = {

    val fileName: String = file.getName
    val tableName: String = fileName.substring(0,fileName.lastIndexOf("."))

    val builder: ImmutableMap.Builder[String, Table] = ImmutableMap.builder()

    val table = new ExcelScannnableTable(file)


    builder.put(tableName,table)
    builder.build()



  }
}
