package com.qingchen.study.maptoobj;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangChen
 * @since 2020-11-30 10:45
 **/
@SuppressWarnings("unchecked")
public class TeaModel {

    public Map<String, Object> toMap() throws IllegalArgumentException, IllegalAccessException {
        Field[] var2 = this.getClass().getDeclaredFields();
        int var3 = var2.length;

        HashMap<String, Object> map = new HashMap(var3);

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            field.setAccessible(true);
            NameInMap anno = field.getAnnotation(NameInMap.class);
            String key;
            if (anno == null) {
                key = field.getName();
            } else {
                key = anno.value();
            }

            if (null != field.get(this) && List.class.isAssignableFrom(field.get(this).getClass())) {
                ParameterizedType listGenericType = (ParameterizedType)field.getGenericType();
                Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
                Type listActualTypeArgument = listActualTypeArguments[0];
                Class<?> itemType = null;
                if (listActualTypeArgument instanceof Class) {
                    itemType = (Class)listActualTypeArgument;
                }

                ArrayList<Object> arrayField = (ArrayList)field.get(this);
                ArrayList<Object> fieldList = new ArrayList(arrayField.size());

                for(int i = 0; i < arrayField.size(); ++i) {
                    if (null != itemType && TeaModel.class.isAssignableFrom(itemType)) {
                        Map<String, Object> fields = ((TeaModel)arrayField.get(i)).toMap();
                        fieldList.add(fields);
                    } else {
                        fieldList.add(arrayField.get(i));
                    }
                }

                map.put(key, fieldList);
            } else if (null != field.get(this) && TeaModel.class.isAssignableFrom(field.get(this).getClass())) {
                TeaModel teaModel = (TeaModel)field.get(this);
                map.put(key, teaModel.toMap());
            } else {
                map.put(key, field.get(this));
            }
        }

        return map;
    }

    public static <T extends TeaModel> T toModel(Map<String, ?> map, T model) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Field[] var2 = model.getClass().getFields();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            NameInMap anno = field.getAnnotation(NameInMap.class);
            String key;
            if (anno == null) {
                key = field.getName();
            } else {
                key = anno.value();
            }

            Object value = map.get(key);
            if (value != null) {
                value = parseNumber(value, field.getType());
                Class<?> clazz = field.getType();
                if (List.class.isAssignableFrom(clazz)) {
                    ArrayList<?> valueList = (ArrayList)value;
                    ParameterizedType listGenericType = (ParameterizedType)field.getGenericType();
                    Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
                    Type listActualTypeArgument = listActualTypeArguments[0];
                    Class<?> itemType = null;
                    if (listActualTypeArgument instanceof Class) {
                        itemType = (Class)listActualTypeArgument;
                    }

                    ArrayList result = new ArrayList();
                    int i;
                    if (null != itemType && TeaModel.class.isAssignableFrom(itemType)) {
                        if (valueList.size() <= 0 || !Map.class.isAssignableFrom(valueList.get(0).getClass())) {
                            for(i = 0; i < valueList.size(); ++i) {
                                result.add(valueList.get(i));
                            }
                        } else {
                            for(i = 0; i < valueList.size(); ++i) {
                                Object teaModel = toModel((Map)valueList.get(i), (TeaModel)itemType.getDeclaredConstructor().newInstance());
                                result.add(teaModel);
                            }
                        }
                    } else {
                        for(i = 0; i < valueList.size(); ++i) {
                            Object teaModel = valueList.get(i);
                            result.add(teaModel);
                        }
                    }

                    field.set(model, result);
                } else if (TeaModel.class.isAssignableFrom(clazz) && Map.class.isAssignableFrom(value.getClass())) {
                    Object data = clazz.getDeclaredConstructor().newInstance();
                    field.set(model, toModel((Map)value, (TeaModel)data));
                } else if (Integer.class.isAssignableFrom(clazz)) {
                    field.set(model, Integer.parseInt(String.valueOf(value)));
                } else if (Double.class.isAssignableFrom(clazz)) {
                    field.set(model, Double.parseDouble(String.valueOf(value)));
                } else if (Long.class.isAssignableFrom(clazz)) {
                    field.set(model, Long.parseLong(String.valueOf(value)));
                } else if (Boolean.class.isAssignableFrom(clazz)) {
                    field.set(model, Boolean.parseBoolean(String.valueOf(value)));
                } else {
                    field.set(model, value);
                }
            }
        }

        return model;
    }

    private static Object parseNumber(Object value, Class clazz) {
        BigDecimal bigDecimal;
        if (!(value instanceof Double) || clazz != Long.class && clazz != Long.TYPE) {
            if (!(value instanceof Double) || clazz != Integer.class && clazz != Integer.TYPE) {
                return value;
            } else {
                bigDecimal = new BigDecimal(value.toString());
                return bigDecimal.intValue();
            }
        } else {
            bigDecimal = new BigDecimal(value.toString());
            return bigDecimal.longValue();
        }
    }



    public static Map<String, Object> buildMap(TeaModel teaModel) throws IllegalAccessException {
        return null == teaModel ? null : teaModel.toMap();
    }


    public static Map<String, Object> toMap(Object object) throws IllegalArgumentException, IllegalAccessException {
        HashMap<String, Object> map = new HashMap();
        if (null != object && object instanceof Map) {
            return (Map)object;
        } else if (null != object && TeaModel.class.isAssignableFrom(object.getClass())) {
            Field[] var2 = object.getClass().getFields();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Field field = var2[var4];
                String key = field.getName();
                if (null != field.get(object) && List.class.isAssignableFrom(field.get(object).getClass())) {
                    ParameterizedType listGenericType = (ParameterizedType)field.getGenericType();
                    Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
                    Type listActualTypeArgument = listActualTypeArguments[0];
                    Class<?> itemType = null;
                    if (listActualTypeArgument instanceof Class) {
                        itemType = (Class)listActualTypeArgument;
                    }

                    ArrayList<Object> arrayField = (ArrayList)field.get(object);
                    ArrayList<Object> fieldList = new ArrayList();

                    for(int i = 0; i < arrayField.size(); ++i) {
                        if (null != itemType && TeaModel.class.isAssignableFrom(itemType)) {
                            Map<String, Object> fields = toMap(arrayField.get(i));
                            fieldList.add(fields);
                        } else {
                            fieldList.add(arrayField.get(i));
                        }
                    }

                    map.put(key, fieldList);
                } else if (null != field.get(object) && TeaModel.class.isAssignableFrom(field.get(object).getClass())) {
                    map.put(key, toMap(field.get(object)));
                } else {
                    map.put(key, field.get(object));
                }
            }

            return map;
        } else {
            return map;
        }
    }

    public static <T extends TeaModel> T build(Map<String, ?> map, T model) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Field[] var2 = model.getClass().getFields();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            String key = field.getName();
            Object value = map.get(key);
            if (value != null) {
                Class<?> clazz = field.getType();
                if (List.class.isAssignableFrom(clazz)) {
                    ArrayList<?> valueList = (ArrayList)value;
                    ParameterizedType listGenericType = (ParameterizedType)field.getGenericType();
                    Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
                    Type listActualTypeArgument = listActualTypeArguments[0];
                    Class<?> itemType = null;
                    if (listActualTypeArgument instanceof Class) {
                        itemType = (Class)listActualTypeArgument;
                    }

                    ArrayList result = new ArrayList();
                    int i;
                    if (null != itemType && TeaModel.class.isAssignableFrom(itemType)) {
                        if (valueList.size() <= 0 || !Map.class.isAssignableFrom(valueList.get(0).getClass())) {
                            for(i = 0; i < valueList.size(); ++i) {
                                result.add(valueList.get(i));
                            }
                        } else {
                            for(i = 0; i < valueList.size(); ++i) {
                                Object teaModel = build((Map)valueList.get(i), (TeaModel)itemType.getDeclaredConstructor().newInstance());
                                result.add(teaModel);
                            }
                        }
                    } else {
                        for(i = 0; i < valueList.size(); ++i) {
                            Object teaModel = valueList.get(i);
                            result.add(teaModel);
                        }
                    }

                    field.set(model, result);
                } else if (TeaModel.class.isAssignableFrom(clazz)) {
                    Object data = clazz.getDeclaredConstructor().newInstance();
                    field.set(model, build(toMap(value), (TeaModel)data));
                } else if (Integer.class.isAssignableFrom(clazz)) {
                    field.set(model, Integer.parseInt(String.valueOf(value)));
                } else if (Double.class.isAssignableFrom(clazz)) {
                    field.set(model, Double.parseDouble(String.valueOf(value)));
                } else if (Long.class.isAssignableFrom(clazz)) {
                    field.set(model, Long.parseLong(String.valueOf(value)));
                } else if (Boolean.class.isAssignableFrom(clazz)) {
                    field.set(model, Boolean.parseBoolean(String.valueOf(value)));
                } else {
                    field.set(model, value);
                }
            }
        }

        return model;
    }
}
