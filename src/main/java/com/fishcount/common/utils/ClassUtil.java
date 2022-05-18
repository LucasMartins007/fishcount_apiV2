package com.fishcount.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.pattern.enums.EnumDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;

import java.io.IOException;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Stream;

/**
 *
 * @author lucas
 */
public class ClassUtil {

    private static final Map<Class, Class> CLASSES = new HashMap<>();

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


    public static Class getClassParameterizedType(Class clazz) {
        return ((Class) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public static Class getClassParameterizedTypeField(Field field) {
        return (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public static final <C> C createInstance(String className, ClassLoader classLoader) {
        final Class<C> clazz = (Class<C>) createClass(className, classLoader);
        return createInstance(clazz);
    }

    @SuppressWarnings("unchecked")
    public static final Class<?> createClass(String className, ClassLoader classLoader) {
        try {
            return ClassUtils.forName(className, classLoader);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static final <C> C createInstance(Class<C> clazz) {
        try {
            final Constructor<C> constructorIfAvailable = ClassUtils.getConstructorIfAvailable(clazz);

            if (constructorIfAvailable != null) {
                return constructorIfAvailable.newInstance();
            } else {
                throw new RuntimeException("All Args Constructor");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isType(Class type, Class aClass) {
        if (type != null && aClass != null) {
            if (!type.isPrimitive() && !aClass.isPrimitive()) {
                return aClass.isAssignableFrom(type);
            }
            Map<Class, Class> decode = new LinkedHashMap<>();

            decode.put(boolean.class, Boolean.class);
            decode.put(double.class, Double.class);
            decode.put(int.class, Integer.class);
            decode.put(float.class, Float.class);
            decode.put(char.class, Character.class);
            decode.put(long.class, Long.class);

            if (type.isPrimitive()) {
                Class decoded = decode.get(type);
                return aClass.isAssignableFrom(decoded);
            } else if (aClass.isPrimitive()) {
                Class decoded = decode.get(aClass);
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

    public static Method getMethod(Class aClass, String method) {
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

    @SuppressWarnings("unchecked")
    public static <T> T cast(Class<T> clzz, Object valor) {
        try {
            if (clzz.equals(Object.class)) {
                return (T) valor;
            }

            if (valor != null) {
                if (clzz.isAssignableFrom(valor.getClass())) {
                    return (T) valor;
                }

                String valString = valor.toString();

                if (isType(clzz, BigDecimal.class)) {
                    return (T) BigDecimalUtil.valueOf(valString);
                } else if (isType(clzz, Boolean.class)) {
                    String key = (valString + "").trim().toLowerCase();
                    return (T) BOOLEAN_VALUES.get(key);
                } else if (clzz.equals(String.class)) {
                    if (Date.class.isAssignableFrom(valor.getClass())) {
                        return (T) DateUtil.formatDDMMYYYY((Date) valor);
                    }
                    return (T) (valor + "");
                } else if (!clzz.isPrimitive()) {
                    if (valor.getClass().equals(clzz)) {
                        return (T) valor;
                    } else if (valor instanceof BigDecimal) {
                        try {
                            BigDecimal big = (BigDecimal) valor;

                            if (clzz.equals(Integer.class)) {
                                valor = big.intValue();
                            } else if (clzz.equals(Double.class)) {
                                valor = big.doubleValue();
                            } else if (clzz.equals(Long.class)) {
                                valor = big.longValue();
                            }
                            return (T) valor;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (clzz.equals(valor.getClass())) {
                        return (T) valor;
                    } else if (valor instanceof Enum) {
                        return (T) valor;
                    } else if (valor instanceof Number) {
                        if (clzz.equals(Integer.class)) {
                            Integer v = ((Number) valor).intValue();
                            return (T) v;
                        } else if (clzz.equals(Double.class)) {
                            Double v = ((Number) valor).doubleValue();
                            return (T) v;
                        } else if (clzz.equals(Long.class)) {
                            Long v = ((Number) valor).longValue();
                            return (T) v;
                        } else if (clzz.equals(Number.class)) {
                            Constructor constructor = clzz.getConstructor(String.class);
                            return (T) constructor.newInstance(valor + "");
                        }
                    } else if (valor instanceof String) {
                        String token = "|";

                        if (valString.contains("|")) {
                            token = "|";
                        } else if (valString.contains("&")) {
                            token = "&";
                        } else if (valString.contains(",")) {
                            token = ",";
                        }
                        String regex = "[" + token + "]";

                        if (isType(clzz, Date.class)) {
                            if (valString.equals("null")) {
                                return null;
                            }

                            String value = valString.trim().toUpperCase();

                            for (Map.Entry<EnumDateFormat, String> entry : DATE_VALUES.entrySet()) {
                                regex = entry.getValue();

                                if (value.matches(regex)) {
                                    try {
                                        return (T) entry.getKey().parse(value);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            return null;
                        } else if (clzz.equals(List.class)) {
                            List lista = new ArrayList();
                            String[] split = Utils.nvl(valString, "").split(regex);

                            for (String s : split) {
                                lista.add(s.trim());
                            }
                            return (T) lista;
                        } else if (clzz.isArray()) {
                            return (T) StringUtils.splitPreserveAllTokens(valString, token);
                        } else if (clzz.equals(Map.class)) {
                            Map<String, String> mapa = new LinkedHashMap();

                            if (valString.startsWith("{") && valString.endsWith("}")) {
                                mapa = new ObjectMapper().readValue(valString, LinkedHashMap.class);
                            } else {
                                List<String> lista = new ArrayList(Arrays.asList(valString.split(regex)));
                                String[] split;

                                for (String str : lista) {
                                    try {
                                        split = str.split("[=]");
                                        if (!Utils.isEmpty(split[0])) {
                                            String v = null;

                                            if (!Utils.isEmpty(split[1])) {
                                                v = split[1].trim();
                                            }
                                            mapa.put(split[0].trim(), v);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            return (T) mapa;
                        }

                        if (Number.class.isAssignableFrom(clzz)) {
                            if (valString.contains(",")) {
                                valString = valString.replace(".", "");
                                valString = valString.replace(",", ".");
                                valor = valString;
                            }

                            if (Utils.isEmpty(valString) || !NumericUtil.isNumeric(valString)) {
                                return null;
                            }
                        }

                        Constructor constructor = clzz.getConstructor(String.class);
                        return (T) constructor.newInstance(valor);

                    } else if (List.class.isAssignableFrom(clzz) && valor instanceof Object[]) {
                        return (T) new ArrayList(Arrays.asList((Object[]) valor));
                    } else {
                        try {
                            Method method = clzz.getDeclaredMethod("valueOf", String.class);
                            return (T) method.invoke(null, valor + "");
                        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            return (T) valor;
                        }
                    }
                } else if (CLASSES.containsKey(clzz)) {
                    clzz = CLASSES.get(clzz);
                    Constructor<T> constructor = clzz.getConstructor(String.class);
                    return (T) constructor.newInstance(valor + "");
                } else {
                    return (T) valor;
                }
            } else if (isType(clzz, Boolean.class) && valor == null) {
                return (T) Boolean.FALSE;
            }
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Method parseHandlerToMethod(Object handler) {
        return handler != null && handler instanceof HandlerMethod ? ((HandlerMethod) handler).getMethod() : null;
    }

    public static <T> T getEnumValue(Class enumClass, String valor) {
        for (Object enumered : enumClass.getEnumConstants()) {
            if (enumered instanceof IEnum) {
                IEnum ienum = ((IEnum) enumered);

                if (ienum.getKey().equals(valor) ||
                        ienum.getValue().equals(valor) ||
                        ienum.getName().equals(valor)) {
                    return (T) enumered;
                }
            }

            if (enumered instanceof Enum) {
                Enum typeEnum = ((Enum) enumered);

                if (typeEnum.name().equals(valor) ||
                        typeEnum.toString().equals(valor)) {
                    return (T) enumered;
                }
            }
        }

        return null;
    }

}
