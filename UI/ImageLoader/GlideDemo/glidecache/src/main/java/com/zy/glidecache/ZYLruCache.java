package com.zy.glidecache;

import java.util.LinkedHashMap;
import java.util.Map;

public class ZYLruCache<T, Y>  {
    private final Map<T, Y> cache = new LinkedHashMap<>(100, 0.75f, true);

}
