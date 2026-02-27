package com.distribuidora.distri.helper;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class UpdateHelper {

    private UpdateHelper() {
    }
    //Asigna el valor si no es null
    public static <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }


    //Asigna el valor si no es null y cumple con otra condición
    public static <T> void setIf(T value, Predicate<T> condition, Consumer<T> setter) {
        if (value != null && condition.test(value)) {
            setter.accept(value);
        }
    }
}
