package cn.schahaha.day01

import java.io.File
import java.util

import org.apache.calcite.linq4j.Enumerator
import org.apache.calcite.rel.`type`.RelDataType
import org.apache.poi.xssf.usermodel.{XSSFCell, XSSFRow, XSSFSheet, XSSFWorkbook}

import scala.collection.mutable.ListBuffer

/**
  * 迭代器
  *
  * @param file
  * @param types
  * @tparam E
  */
class ExcelEnumerator[E](val file:File,val types:java.util.List[RelDataType]) extends Enumerator[E]{

  //todo  完成迭代器的方法
  override def moveNext(): Boolean = ???

  override def current(): E = ???

  override def reset(): Unit = ???

  override def close(): Unit = ???
}
