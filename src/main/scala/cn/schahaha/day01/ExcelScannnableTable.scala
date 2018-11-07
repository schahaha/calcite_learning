package cn.schahaha.day01

import java.io.File

import org.apache.calcite.DataContext
import org.apache.calcite.linq4j.{AbstractEnumerable, Enumerable, Enumerator}
import org.apache.calcite.rel.`type`.{RelDataType, RelDataTypeFactory}
import org.apache.calcite.schema.ScannableTable

 class ExcelScannnableTable(override val file:File) extends  ExcelTable( file ) with ScannableTable{


   override def scan(dataContext: DataContext): Enumerable[Array[AnyRef]] = {


    new AbstractEnumerable[Array[AnyRef]] {


      override def enumerator(): Enumerator[Array[AnyRef]] = {


        new ExcelEnumerator[Array[AnyRef]](file,types)

      }
    }


   }


 }
