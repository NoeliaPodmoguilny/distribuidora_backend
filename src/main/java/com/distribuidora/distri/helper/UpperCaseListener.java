package com.distribuidora.distri.helper;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.lang.reflect.Field;

public class UpperCaseListener {

    @PrePersist
    @PreUpdate
    public void upperCaseStrings(Object entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            // Solo Strings y NO la contraseña
            if (field.getType().equals(String.class) && !field.getName().equalsIgnoreCase("contraseña")) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(entity);
                    if (value != null) {
                        field.set(entity, value.trim().toUpperCase());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error al intentar convertir el string a mayúsculas", e);
                }
            }
        }
    }
}
