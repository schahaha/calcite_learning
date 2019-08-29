package com.shicheng.java;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.util.Map;


public class MySchemaFactory implements SchemaFactory {
    @Override
    public Schema create(SchemaPlus schemaPlus, String s, Map<String, Object> map) {


        String filePath = map.get("filePath").toString();

        TextSchema textSchema = new TextSchema(filePath);


        return textSchema;
    }
}
