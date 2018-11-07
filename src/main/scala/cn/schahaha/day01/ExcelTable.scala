package cn.schahaha.day01

import java.io.File
import java.util

import org.apache.calcite.rel.`type`.{RelDataType, RelDataTypeFactory}
import org.apache.calcite.schema.impl.AbstractTable
import org.apache.poi.xssf.usermodel.{XSSFCell, XSSFRow, XSSFSheet, XSSFWorkbook}

import scala.util.control.Breaks

/**
  * 定义顶层excel 表格
  * @param file
  */
abstract  class  ExcelTable(val file:File)  extends AbstractTable{

  var types=new util.ArrayList[RelDataType]()
  var rawTypes=new util.ArrayList[String]()




  override def getRowType(relDataTypeFactory: RelDataTypeFactory): RelDataType = {

    val names=new util.ArrayList[String]()
    val types=new util.ArrayList[RelDataType]()

    val dataType: RelDataType = getFirstLineType(file,relDataTypeFactory,names)


    dataType

  }


  protected def getFirstLineType(file:File,relDataTypeFactory:RelDataTypeFactory,names:util.ArrayList[String])={




    if(!file.exists()){

      println("file :"+file+" doesn't exist,please check!")
      throw  new RuntimeException
    }

    val workbook = new XSSFWorkbook(file)
    val sheet: XSSFSheet = workbook.getSheetAt(0)

    val breaks = new Breaks




    //解析列名
    var count=0
    breaks.breakable{

      val row0: XSSFRow = sheet.getRow(0)


      while(true){

        try{

          val name: String = row0.getCell(count).getStringCellValue
          names.add(name)
          count=count+1

        }catch {

          case ex:Exception=>{

            breaks.break()
          }

        }

      }

    }
    rawTypes.clear()

    //获取每列的类型
    val row1: XSSFRow = sheet.getRow(1)
    for(x<- 0 to count-1){


      val cell: XSSFCell = row1.getCell(x)

      if(cell!=null){

        val value: String = cell.getRawValue

        val rawType: String = getFiledRawType(value)

        types.add(relDataTypeFactory.createJavaType(ExcelFieldType.MAP.get(rawType)))

      }


    }


    val dataType: RelDataType = relDataTypeFactory.createStructType(types,names)

    workbook.close()

    dataType












  }


  protected def getFiledRawType(line:String):String={


    //判断是否是数字

    try{

      line.toDouble

      try{

        line.toInt
        rawTypes.add("int")
        //是整数
        "int"

      }catch {
        case  ex:Exception=>{
          //是小数,不是整数
          rawTypes.add("double")
          "double"

        }

      }

    }catch {

      case ex:Exception=>{
        //不是数字
        rawTypes.add("string")
        "string"

      }

    }


  }





}
