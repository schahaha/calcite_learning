package cn.schahaha.day01

import java.io.File
import java.util

import org.apache.calcite.schema.{Schema, SchemaFactory, SchemaPlus}

/**
  * 自定义schema的工厂
  */
class ExcelSchemaFactory extends SchemaFactory{

  /**
    * 创建schema的方法
    * @param schemaPlus 父节点,一般为root
    * @param name schema的名称,即json配置文件里面所配置的schema名称(数据库名称)
    * @param map  json配置文件里面的operand信息
    * @return
    */
  override def create(schemaPlus: SchemaPlus, name: String, map: util.Map[String, AnyRef]): Schema = {


    //这里修改一点内容,将扫描文件夹改为扫描文件, 一个库里面只有一张表格

    val filePath: String = map.get("filepath").toString

    if(!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")){

      println(filePath+" is not a proper  excel file!")
      throw new RuntimeException
    }

    new ExcelSchema(new File(filePath))

  }
}
