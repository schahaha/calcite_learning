package com.shicheng.java;

import com.google.common.collect.Multimap;
import org.apache.calcite.linq4j.tree.Expression;
import org.apache.calcite.schema.Function;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里的schema可以简单的理解为一个数据库，类似于mysql中的一个database。
 * 当然，schema是可以嵌套的，但是不建议这么使用。
 */
public class TextSchema extends AbstractSchema {

    private String filePath;


    public TextSchema() {

    }

    public TextSchema(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean isMutable() {
        return super.isMutable();
    }

    @Override
    public boolean contentsHaveChangedSince(long lastCheck, long now) {
        return super.contentsHaveChangedSince(lastCheck, now);
    }

    @Override
    public Expression getExpression(SchemaPlus parentSchema, String name) {
        return super.getExpression(parentSchema, name);
    }

    @Override
    /**
     * 该方法是获取该schema(database)下面所有的table,实际过程中，应该使用数据库的方式去获取。
     */
    protected Map<String, Table> getTableMap() {


        HashMap<String, Table> map = new HashMap<>();
        map.put("TABLE1",new TextScannerTable(filePath,"TABLE1"));
        return map;
    }

    @Override
    protected Multimap<String, Function> getFunctionMultimap() {
        return super.getFunctionMultimap();
    }

    @Override
    protected Map<String, Schema> getSubSchemaMap() {
        return super.getSubSchemaMap();
    }
}
