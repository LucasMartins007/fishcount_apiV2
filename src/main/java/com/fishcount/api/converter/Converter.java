package com.fishcount.api.converter;

import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.dto.pattern.EnumPadraoDTO;
import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.pattern.AbstractEntity;
import com.fishcount.common.model.pattern.IIdentifier;
import com.fishcount.common.model.pattern.annotations.converter.MappedFieldPropsChildren;
import com.fishcount.common.model.pattern.annotations.converter.OnlyField;
import com.fishcount.common.model.pattern.annotations.converter.TransientFieldDTO;
import com.fishcount.common.utils.ClassUtil;
import com.fishcount.common.utils.ListUtil;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author lucas
 * @param <DTO>
 * @param <Entity>
 */
public class Converter<DTO extends AbstractDTO<?>, Entity extends AbstractEntity<?>> {

    private Class<DTO> dtoClass;

    private Class<Entity> entityClass;

    public Converter() {
        Type[] actualTypeArguments = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        this.dtoClass = (Class<DTO>) actualTypeArguments[0];
        this.entityClass = (Class<Entity>) actualTypeArguments[1];
    }

    public Entity converterDTOParaEntity(DTO dto) {
        return converterDTOParaEntity(dto, this.entityClass);
    }

    public List<Entity> converterDTOParaEntity(List<DTO> dtos) {
        return converterDTOParaEntity(dtos, this.entityClass);
    }

    public DTO converterEntityParaDTO(Entity entity) {
        return converterEntityParaDTO(entity, this.dtoClass);
    }

    public List<DTO> converterEntityParaDTO(List<Entity> entitys) {
        return converterEntityParaDTO(entitys, this.dtoClass);
    }

    public static <D extends AbstractDTO<?>, E extends AbstractEntity> E converterDTOParaEntity(D dto, Class<E> clazzEntity) {
        return (E) converter(dto, clazzEntity);
    }

    public static <D extends AbstractDTO<?>, E extends AbstractEntity> List<E> converterDTOParaEntity(List<D> dtos, Class<E> clazzEntity) {
        return dtos.stream().map(dto -> (E) converter(dto, clazzEntity)).collect(Collectors.toList());
    }

    public static <D extends AbstractDTO<?>, E extends AbstractEntity> D converterEntityParaDTO(E entity, Class<D> clazzDto) {
        return (D) converter(entity, clazzDto);
    }

    public static <D extends AbstractDTO<?>, E extends AbstractEntity> List<D> converterEntityParaDTO(List<E> entitys, Class<D> clazzDto) {
        return entitys.stream().map(entity -> (D) converter(entity, clazzDto)).collect(Collectors.toList());
    }

    private static Object converter(Object objectOrigem, Class classDestino) {
        return converter(objectOrigem, classDestino, null);
    }

    private static Object converter(Object objectOrigem, Class classDestino, List<Field> fieldsEquals) {
        try {
            if (Objects.isNull(objectOrigem) || Objects.isNull(classDestino)) {
                return null;
            }

            if (ListUtil.isNullOrEmpty(fieldsEquals)) {
                fieldsEquals = getEqualsFields(objectOrigem.getClass(), classDestino);
            }

            if (Objects.isNull(fieldsEquals)) {
                return null;
            }

            Map<String, Object> toMapPopulateObjectDesino = new HashMap<>();

            fieldsEquals.stream().forEach(field -> {
                final String pathField = getPathFieldOrPathMappedFieldDTO(field);
                final Object valueFieldOrigem = getValueField(objectOrigem, pathField);
                final Object valueFieldTypeDTO = getValueFieldTypeDTO(valueFieldOrigem, field);

                Object value = Objects.nonNull(valueFieldTypeDTO) ? valueFieldTypeDTO : valueFieldOrigem;

                if (value != null) {
                    value = parseEnum(value, field);

                    toMapPopulateObjectDesino.put(field.getName(), value);
                }
            });

            return popular(toMapPopulateObjectDesino, classDestino);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private static Object getValueFieldTypeDTO(Object valueFieldOrigem, Field field) {
        try {
            if (valueFieldOrigem != null) {
                final Class<?> clazzValue = valueFieldOrigem.getClass();
                final Class<?> clazzField = field.getType();

                if (!clazzValue.equals(clazzField)) {
                    final List<Field> fieldsToConvert = getFieldsFromOnlyField(field, clazzField);

                    if (IIdentifier.class.isAssignableFrom(clazzValue) && IIdentifier.class.isAssignableFrom(clazzField)) {
                        return converter(valueFieldOrigem, clazzField, fieldsToConvert);
                    }

                    if (Collection.class.isAssignableFrom(clazzValue) && Collection.class.isAssignableFrom(clazzField)) {
                        final Class classFieldTypeCollection = ClassUtil.getClassParameterizedTypeField(field);
                        if (IIdentifier.class.isAssignableFrom(classFieldTypeCollection)) {
                            return ((Collection) valueFieldOrigem).stream()
                                    .map(value -> converter(value, classFieldTypeCollection, fieldsToConvert))
                                    .collect(Collectors.toList());
                        }
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private static List<Field> getEqualsFields(Class classOrigem, Class classDestino) {
        final List<Field> fieldsOrigem = new ArrayList();
        addIfNotExistsFieldModifier(fieldsOrigem, Arrays.asList(classOrigem.getDeclaredFields()));
        addIfNotExistsFieldModifier(fieldsOrigem, Arrays.asList(classOrigem.getSuperclass().getDeclaredFields()));

        final List<Field> fieldsDestino = new ArrayList();
        addIfNotExistsFieldModifier(fieldsDestino, Arrays.asList(classDestino.getDeclaredFields()));
        addIfNotExistsFieldModifier(fieldsDestino, Arrays.asList(classDestino.getSuperclass().getDeclaredFields()));

        List<Field> fieldsDestIsMappedFieldDTO = fieldsDestino.stream()
                .filter(fd -> fd.isAnnotationPresent(MappedFieldPropsChildren.class))
                .collect(Collectors.toList());

        List<Field> fieldsTransientFieldDTO = fieldsDestino.stream()
                .filter(fd -> fd.isAnnotationPresent(TransientFieldDTO.class))
                .collect(Collectors.toList());

        List<Field> fieldsEqualsOrigiDest = fieldsOrigem.stream()
                .flatMap(fo -> fieldsDestino.stream().filter(fd -> fd.getName().equalsIgnoreCase(fo.getName())))
                .collect(Collectors.toList());

        final List<Field> fields = new ArrayList<>();

        if (!ListUtil.isNullOrEmpty(fieldsDestIsMappedFieldDTO)) {
            fields.addAll(fieldsDestIsMappedFieldDTO);
        }

        if (!ListUtil.isNullOrEmpty(fieldsEqualsOrigiDest)) {
            fields.addAll(fieldsEqualsOrigiDest);
        }

        if (!ListUtil.isNullOrEmpty(fieldsTransientFieldDTO)) {
            fields.removeAll(fieldsTransientFieldDTO);
        }

        if (AbstractDTO.class.isAssignableFrom(classDestino)) {
            final List<Field> fieldsNotDeclared = getFieldsNotDeclared(classOrigem, fieldsDestino, fieldsEqualsOrigiDest);
            ListUtil.addAllIfNotNull(fields, fieldsNotDeclared);
        }

        return fields;
    }

    private static List<Field> getFieldsNotDeclared(Class classOrigem, List<Field> fieldsDestino, List<Field> fieldsOrigem) {
        final List<Field> fields = new ArrayList<>();

        fieldsDestino.removeAll(fieldsOrigem);
        if (ListUtil.isNotNullOrNotEmpty(fieldsDestino)) {
            for (Field field : fieldsDestino) {
                final Method function = ClassUtil.getGetterMethod(field.getName(), classOrigem);
                if (function != null) {
                    fields.add(field);
                }
            }
        }

        return fields;
    }

    private static List<Field> getFieldsFromOnlyField(Field field, Class<?> clazzValue) {
        if (field.isAnnotationPresent(OnlyField.class)) {
            if (Collection.class.isAssignableFrom(clazzValue) && Collection.class.isAssignableFrom(field.getType())) {
                clazzValue = ClassUtil.getClassParameterizedTypeField(field);
            }

            final List<String> valuesOnlyField = Arrays.asList(field.getAnnotation(OnlyField.class).value());

            final List<Field> fieldsOrigem = new ArrayList();
            addIfNotExistsFieldModifier(fieldsOrigem, Arrays.asList(clazzValue.getDeclaredFields()));
            addIfNotExistsFieldModifier(fieldsOrigem, Arrays.asList(clazzValue.getSuperclass().getDeclaredFields()));

            return valuesOnlyField.stream().flatMap(s -> fieldsOrigem.stream().filter(fd -> fd.getName().equalsIgnoreCase(s))).collect(Collectors.toList());
        }

        return null;
    }

    private static String getPathFieldOrPathMappedFieldDTO(Field field) {
        if (field.isAnnotationPresent(MappedFieldPropsChildren.class)) {
            String value = field.getAnnotation(MappedFieldPropsChildren.class).value();
            return value != null ? value : field.getName();
        } else {
            return field.getName();
        }
    }

    private static Object getValueField(Object objectOrigem, String pathField) {
        if (Objects.nonNull(objectOrigem) && Objects.nonNull(pathField)) {
            return BeanInvokeDynamic.getFieldValue(objectOrigem, pathField);
        }
        return null;
    }

    private static Object parseEnum(Object value, Field field) {
        if (field.getType().isAssignableFrom(EnumPadraoDTO.class)) {
            if (value instanceof Enum) {
                value = new EnumPadraoDTO((IEnum<?>) value);
            }
        }

        if (!value.getClass().equals(field.getType())) {
            if (field.getType().isEnum() && value instanceof EnumPadraoDTO) {
                final EnumPadraoDTO padraoDTO = (EnumPadraoDTO) value;

                value = Stream.of(field.getType().getEnumConstants())
                        .map(valueEnum -> ((Enum) valueEnum))
                        .filter(valueEnum -> {
                            return valueEnum.name().equalsIgnoreCase((String) padraoDTO.getKey())
                                    || valueEnum.name().equalsIgnoreCase((String) padraoDTO.getName());
                        })
                        .findFirst()
                        .orElseThrow(() -> new FcRuntimeException(EnumFcInfraException.ENUM_NOT_FOUND, padraoDTO.getKey()));
            }
        }
        return value;
    }

    private static Object popular(Map propertiesToMap, Class classDestino) {
        try {
            Object instanceObjectDestino = classDestino.newInstance();
            BeanUtilsBean.getInstance().populate(instanceObjectDestino, propertiesToMap);
            return instanceObjectDestino;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private static void addIfNotExistsFieldModifier(Collection<Field> collectionsFields, List<Field> fields) {
        fields.forEach(field -> {
            if (!collectionsFields.contains(field)) {
                if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                    collectionsFields.add(field);
                }
            }
        });
    }
}
