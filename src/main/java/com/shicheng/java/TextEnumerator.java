package com.shicheng.java;

import org.apache.calcite.linq4j.Enumerator;

import java.io.*;

/**
 * 迭代器：一行一行得读取
 * 这里是底层读取的工具类，文件的真实读取都发生在这里，所以需要拿到文件读取的信息，比如文件类型，文件地址等。
 * @param <T>
 */
public class TextEnumerator<T> implements Enumerator<T> {

    private String filePath;


    public TextEnumerator(String filePath) {
        this.filePath = filePath;
    }

    private Object[] values;

    private BufferedReader reader;


    private void check(){

        if(reader==null){

            try {
                reader=new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }




    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public T current() {

        check();

        return (T)values;
    }

    @Override
    /**
     * 外面会有一个迭代器一直调用这个方法，如果该方法返回true，就继续调用current()方法,然后继续循环。返回false，退出。
     */
    public boolean moveNext() {

        check();
        try {
            String newLine = reader.readLine();
            if(newLine!=null){
                String[] split = newLine.split("\t");
                values=split;
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        return false;
    }

    @Override
    public void reset() {

        check();

        try {
            reader.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void close() {

        check();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
