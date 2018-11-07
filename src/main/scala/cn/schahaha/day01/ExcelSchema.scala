package cn.schahaha.day01

import java.util

import com.google.common.collect.Multimap
import org.apache.calcite.linq4j.tree.Expression
import org.apache.calcite.schema
import org.apache.calcite.schema.{Schema, SchemaPlus, Table}
import org.apache.calcite.schema.impl.AbstractSchema

/**
  * 自定义一种用于读取excel的schema
  */

class ExcelSchema extends AbstractSchema{



  override def getTableMap: util.Map[String, Table] = {






  }
}
