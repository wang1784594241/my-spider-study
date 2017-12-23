package com.mww.gecco;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    int top() default 1;

    int last() default 1;

    int begin() default 1;

    int end() default 1;

    boolean isTop() default false;

    boolean isLast() default false;

    boolean isRange() default false;
}