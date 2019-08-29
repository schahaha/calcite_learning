package com.shicheng.java;

import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class TextEnumerable extends AbstractEnumerable {

    private String filePath;

    public TextEnumerable(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Enumerator enumerator() {
        return new TextEnumerator<String>(filePath);
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}
