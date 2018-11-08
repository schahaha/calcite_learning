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
class ExcelEnumerator[E](val file:File,val types:java.util.List[String]) extends Enumerator[E]{


  val workbook=new XSSFWorkbook(file)
  private val sheet: XSSFSheet = workbook.getSheetAt(0)
  var count:Int=1
  var currentValue:E=_

  //todo  完成迭代器的方法, value的获取需要根据field的类型来判断,不能统一用一种类型
  //todo   字段类型需要进一步研究, 如果要用到高级查询, 需要用类型去匹配....
  override def moveNext(): Boolean = {

    val row: XSSFRow = sheet.getRow(count)

    val line=new util.ArrayList[Object]()


    if(row!=null){
      for(x<- 0 to types.size()-1){

        val cell: XSSFCell = row.getCell(x)
        if(cell!=null){





          line.add(

            types.get(x) match {


              case "string"=>cell.getStringCellValue.asInstanceOf[Object]
              case _=>cell.getNumericCellValue.asInstanceOf[Object]

            }

          )


        }else{

          line.add("")
        }



      }

     currentValue=  line.toArray.asInstanceOf[E]

      count=count+1
      true
    }else{
      false
    }






  }

  override def current(): E = {


//    val array: Array[AnyRef] = currentValue.asInstanceOf[Array[AnyRef]]
//
//    for(x<- array){
//
//      print(x   +"    ")
//    }
//    println()



    currentValue


  }

  override def reset(): Unit = {


  }

  override def close(): Unit = {
    workbook.close()
  }
}
