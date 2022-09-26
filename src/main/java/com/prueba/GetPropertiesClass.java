package com.prueba;

import com.prueba.entity.Car;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class GetPropertiesClass {
    public static  void main(String[] args){

        /*try {
            Car c = new Car();
            Class cls = c.getClass();

            // returns the array of Field objects
            Field[] fields = cls.getDeclaredFields();
            for(int i = 0; i < fields.length; i++) {
                System.out.println("Field = " + fields[i].toString());
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    public String marshallObject(K object) {
        log.info("Marshalling object of Formattable Class[" + this.clazz + "]");
        InternalFormat internalFormat = new InternalFormat();
        Field[] var3 = this.clazz.getDeclaredFields();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];

            try {
                Object fieldValue = PropertyUtils.getProperty(object, field.getName());
                if (fieldValue != null) {
                    internalFormat.put(field.getName(), fieldValue);
                }
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var8) {
                throw new FormatterException("Could not marshall message, field[" + field.getName() + "] could not be accessed.");
            }
        }

        return new String(super.getFramesAsArray(internalFormat));*/
    }
}
