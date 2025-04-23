package com.sr.L.DShop.utils;

import java.lang.reflect.Field;

public class PatchHelper {
    public static void patchNonNullFields(Object source, Object target) throws NoSuchFieldException,IllegalAccessException{

        Field[] fields = source.getClass().getFields();
        for(Field field : fields){
            field.setAccessible(true);

            if(field.getName().equalsIgnoreCase("id")){
                continue;
            }

            Object value = field.get(source);
                if(value!=null){
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target,value);
                }
        }
    }
}
