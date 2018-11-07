package cn.schahaha.day01

import java.io.File

import org.apache.calcite.linq4j.Enumerator
import org.apache.poi.xssf.usermodel.{XSSFCell, XSSFRow, XSSFSheet, XSSFWorkbook}

class ExcelEnumerator[E](val file:File,val types:java.util.List[String]) extends Enumerator[E]{

   val workbook = new XSSFWorkbook(file)
     val sheet: XSSFSheet = workbook.getSheetAt(0)
   var current:Object= _

   var count:Int=0


  override def moveNext(): Boolean = {



  }

  override def current(): E = {

    current

  }

  override def reset(): Unit = {



  }

  override def close(): Unit = {

    workbook.close()



  }

  def getColumns():Unit={

    val row: XSSFRow = sheet.getRow(count)

    for(x<-0 to types.size()-1){

      val cell: XSSFCell = row.getCell(x)

      if(cell==null){


      }




    }






  }
}
