package com.fishcount.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.pattern.enums.EnumDateFormat;

import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;

/**
 *
 * @author lucas
 */

public class ClassUtil {

    ClassUtil() {
    }

    private static final Map<Class<?>, Class<?>> CLASSES = new HashMap<>();

    private static final Map<String, Boolean> BOOLEAN_VALUES = new LinkedHashMap<>();

    private static final Map<EnumDateFormat, String> DATE_VALUES = new LinkedHashMap<>();

    static {
        DATE_VALUES.put(EnumDateFormat.YYYYMMDD, "[0-9]{4}-[0-9]{2}-[0-9]{2}");
        DATE_VALUES.put(EnumDateFormat.YYYYMMDDTHHMMSS, "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}");
        DATE_VALUES.put(EnumDateFormat.YYYYMMDDHHMMSS, "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        DATE_VALUES.put(EnumDateFormat.DDMMYYYY, "[0-9]{2}/[0-9]{2}/[0-9]{4}");
        DATE_VALUES.put(EnumDateFormat.DDMMYYYYHHMMSS, "[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}");

        CLASSES.put(boolean.class, Boolean.class);
        CLASSES.put(char.class, Character.class);
        CLASSES.put(byte.class, Byte.class);
        CLASSES.put(short.class, Short.class);
        CLASSES.put(long.class, Long.class);
        CLASSES.put(float.class, Float.class);
        CLASSES.put(int.class, Integer.class);
        CLASSES.put(double.class, Double.class);
        CLASSES.put(void.class, Void.class);

        BOOLEAN_VALUES.put("1", true);
        BOOLEAN_VALUES.put("0", false);
        BOOLEAN_VALUES.put("s", true);
        BOOLEAN_VALUES.put("n", false);
        BOOLEAN_VALUES.put("v", true);
        BOOLEAN_VALUES.put("f", false);
        BOOLEAN_VALUES.put("sim", true);
        BOOLEAN_VALUES.put("nao", false);
        BOOLEAN_VALUES.put("verdadeiro", true);
        BOOLEAN_VALUES.put("falso", false);
        BOOLEAN_VALUES.put("on", true);
        BOOLEAN_VALUES.put("off", false);
        BOOLEAN_VALUES.put("yes", true);
        BOOLEAN_VALUES.put("no", false);
        BOOLEAN_VALUES.put("y", true);
        BOOLEAN_VALUES.put("n", false);
        BOOLEAN_VALUES.put("true", true);
        BOOLEAN_VALUES.put("false", false);
    }

    public static Class<?> getClassParameterizedType(Class<?> clazz) {
        return ((Class<?>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public static Class<?> getClassParameterizedTypeField(Field field) {
        return (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public static final <C> C createInstance(String className, ClassLoader classLoader) {
        final Class<C> clazz = (Class<C>) createClass(className, classLoader);
        return createInstance(clazz);
    }

    public static final Class<?> createClass(String className, ClassLoader classLoader) {
        try {
            return ClassUtils.forName(className, classLoader);
        } catch (ClassNotFoundException e) {
            throw new FcRuntimeException(e);
        }
    }

    public static final <C> C createInstance(Class<C> clazz) {
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
        for (Object enumered : enumClass.getEnumConstants()) {
            if (enumered instanceof IEnum) {
                IEnum<T> ienum = ((IEnum<T>) enumered);

                if (ienum.getKey().equals(valor) ||
                        ienum.getValue().equals(valor) ||
                        ienum.getName().equals(valor)) {
                    return (T) enumered;
                }
            }

            if (enumered instanceof Enum) {
                Enum<?> typeEnum = ((Enum<?>) enumered);

                if (typeEnum.name().equals(valor) ||
                        typeEnum.toString().equals(valor)) {
                    return (T) enumered;
                }
            }
        }

        return null;
    }

}
