package com.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Headers {

    private final String[] namesAndValues;

    Headers(Builder builder) {
        this.namesAndValues = builder.namesAndValues.toArray(new String[builder.namesAndValues.size()]);
    }

    public Builder newBuilder() {
        Builder result = new Builder();
        Collections.addAll(result.namesAndValues, namesAndValues);
        return result;
    }


    @Override
    public String toString() {
        return "Headers{" +
                "namesAndValues=" + Arrays.toString(namesAndValues) +
                '}';
    }

    public String get(String name) {
        return get(namesAndValues, name);
    }

    private static String get(String[] namesAndValues, String name) {
        for (int i = namesAndValues.length - 2; i >= 0; i -= 2) {
            if (name.equalsIgnoreCase(namesAndValues[i])) {
                return namesAndValues[i + 1];
            }
        }
        return null;
    }


    public int size() {
        return namesAndValues.length / 2;
    }

    public String name(int index) {
        return namesAndValues[index * 2];
    }

    public String value(int index) {
        return namesAndValues[index * 2 + 1];
    }

    public static final class Builder {
        final List<String> namesAndValues = new ArrayList<>(20);


        public Builder add(String name, String value) {
            //checkNameAndValue(name, value);
            return addLenient(name, value);
        }

        Builder addLenient(String name, String value) {
            namesAndValues.add(name);
            namesAndValues.add(value.trim());
            return this;
        }

        public Headers build() {
            return new Headers(this);
        }
    }
}
