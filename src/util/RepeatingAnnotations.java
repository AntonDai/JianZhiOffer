package util;

import java.lang.annotation.*;

/**
 * @description: 重复注解测试
 * @author: Daniel
 * @create: 2019-04-08-17-32
 **/
public class RepeatingAnnotations {
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    }

    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable {

    }

    public static void main(String[] args) {
        for(Filter filter : Filterable.class.getAnnotationsByType(Filter.class))
            System.out.println(filter.value());
    }
}
