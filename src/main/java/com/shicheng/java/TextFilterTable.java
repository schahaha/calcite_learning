package com.shicheng.java;

import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.schema.FilterableTable;
import org.apache.calcite.sql.type.SqlTypeName;

import java.util.List;

/**
 * 比SannTable稍微复杂点的类型。
 * 同样也是调用一个scan方法，然后返回一个迭代器，不过这个scan方法里面可以带有一个filters对象，传给
 * 迭代器后，可以在迭代的时候直接过滤，就不需要全部读取出来，放在内存里面过滤，减小计算量。
 * 和scannertable 比起来，算是谓词下推了。不过效率还是很低下，都是一次性读出来一行，而且没一行都需要读取，
 * 不符合要求，丢弃而已。不建议使用。
 */
public class TextFilterTable extends TextTable implements FilterableTable {
    @Override
    /**
     * 和ScannableTable基本一致，但是 有一个过滤器，可以传给迭代器。
     * 过滤的逻辑，需要在迭代器里面实现。
     */
    public Enumerable<Object[]> scan(DataContext root, List<RexNode> filters) {


        return null;
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
}
