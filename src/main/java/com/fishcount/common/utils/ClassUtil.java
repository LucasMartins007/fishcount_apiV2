package com.fishcount.common.utils;

import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.pattern.enums.EnumDateFormat;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author lucas
 */

public class ClassUtil {

    ClassUtil() {
    }

    public static Class<?> getClassParameterizedType(Class<?> clazz) {
        return ((Class<?>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public static Class<?> getClassParameterizedTypeField(Field field) {
        return (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public static <C> C createInstance(String className, ClassLoader classLoader) {
        final Class<C> clazz = (Class<C>) createClass(className, classLoader);
        return createInstance(clazz);
    }

    public static Class<?> createClass(String className, ClassLoader classLoader) {
        try {
            return ClassUtils.forName(className, classLoader);
        } catch (ClassNotFoundException e) {
            throw new FcRuntimeException(e);
        }
    }

    public static <C> C createInstance(Class<C> clazz) {
        try {
            final Constructor<C> constructorIfAvailable = ClassUtils.getConstructorIfAvailable(clazz);

            if (constructorIfAvailable != null) {
                return constructorIfAvailable.newInstance();
            } else {
                throw new FcRuntimeException("All Args Constructor");
            }
        } catch (Exception e) {
            throw new FcRuntimeException(e);
        }
    }

    public static boolean isType(Class<?> type, Class<?> aClass) {
        if (type != null && aClass != null) {
            if (!type.isPrimitive() && !aClass.isPrimitive()) {
                return aClass.isAssignableFrom(type);
            }
            Map<Class<?>, Class<?>> decode = new LinkedHashMap<>();

            decode.put(boolean.class, Boolean.class);
            decode.put(double.class, Double.class);
            decode.put(int.class, Integer.class);
            decode.put(float.class, Float.class);
            decode.put(char.class, Character.class);
            decode.put(long.class, Long.class);

            if (type.isPrimitive()) {
                Class<?> decoded = decode.get(type);
                return aClass.isAssignableFrom(decoded);
            } else if (aClass.isPrimitive()) {
                Class<?> decoded = decode.get(aClass);
                return type.isAssignableFrom(decoded);
            }
        }
        return false;
    }

    public static Method getGetterMethod(String fieldName, Class<?> javaBeanClass) {
        return Stream.of(javaBeanClass.getDeclaredMethods())
                .filter(method -> ClassUtil.isGetterMethod(method, fieldName))
                .findFirst()
                .orElse(null);
    }

    private static boolean isGetterMethod(Method method, String name) {
        return method.getParameterCount() == 0
                && !Modifier.isStatic(method.getModifiers())
                && (method.getName().equalsIgnoreCase("get" + name) || method.getName().equalsIgnoreCase("is" + name));
    }

    public static Method getMethod(Class<?> aClass, String method) {
        for (Method m : aClass.getMethods()) {
            if (m.getName().equalsIgnoreCase(method)) {
                return m;
            }
        }

        for (Method m : aClass.getDeclaredMethods()) {
            if (m.getName().equalsIgnoreCase(method)) {
                return m;
            }
        }
        return null;
    }

    public static Method parseHandlerToMethod(Object handler) {
        return handler instanceof HandlerMethod ? ((HandlerMethod) handler).getMethod() : null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getEnumValue(Class<T> enumClass, String valor) {
        for (T enumered : enumClass.getEnumConstants()) {
            if (enumered instanceof IEnum) {
                IEnum<T> ienum = ((IEnum<T>) enumered);

                if (ienum.getKey().equals(valor) ||
                        ienum.getValue().equals(valor) ||
                        ienum.getName().equals(valor)) {
                    return enumered;
                }
            }

            if (enumered instanceof Enum) {
                Enum<?> typeEnum = ((Enum<?>) enumered);

                if (typeEnum.name().equals(valor) ||
                        typeEnum.toString().equals(valor)) {
                    return enumered;
                }
            }
        }

        return null;
    }

}
