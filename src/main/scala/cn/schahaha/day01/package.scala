package cn.schahaha

import scala.collection.mutable.ListBuffer

package object day01 {

  implicit def javaListToListBuffer[E]( javaList:java.util.ArrayList[E]):ListBuffer[E]={

    val list=ListBuffer[E]()

    for(x<- 0 to javaList.size()-1){

      list.append(javaList.get(x))
    }

    list



  }



}
