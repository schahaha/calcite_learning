package com.shicheng.java;

import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.sql.type.SqlTypeName;

/**
 * 简单的方式：
 * 直接继承AbstractTable（相当于BaseTable）,拥有一些基本功能后，再继续实现功能，比如ScannableTable
 *
 * 这种实现方式，SQL中的所有操作，都是通过扫描全表而得来的，然后在进行加工，效率是非常低下的。仅仅是试验用而已。
 */
public class TextScannerTable extends TextTable implements ScannableTable {

    private String filePath;

    private String tableName;

    public TextScannerTable(String filePath, String tableName) {
        this.filePath = filePath;
        this.tableName = tableName;
    }


    @Override
    /**
     * 获取这张表的字段信息，实际过程中，使用元数据进行管理。
     */
    public RelDataType getRowType(RelDataTypeFactory relDataTypeFactory) {
        RelDataTypeFactory.FieldInfoBuilder builder = relDataTypeFactory.builder();
        //这里暂且写死

        builder.add("ID", SqlTypeName.INTEGER);
        builder.add("NAME",SqlTypeName.VARCHAR);
        builder.add("SEX",SqlTypeName.INTEGER);
        builder.add("SALARY",SqlTypeName.DOUBLE);

        return builder.build();
    }

    @Override
    public Enumerable<Object[]> scan(DataContext root) {

        AbstractEnumerable<Object[]> enumerable = new AbstractEnumerable<Object[]>() {


            @Override
            public Enumerator enumerator() {
                return new TextEnumerator(filePath);
            }
        };


        return enumerable;
    }
}
