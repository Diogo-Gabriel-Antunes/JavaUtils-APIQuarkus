package org.acme.Util;


import org.acme.Anotacao.DTO.Type;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.Util.InterfacesUtil.Invoker;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.exceptions.ValidacaoException;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

@ApplicationScoped
public class FieldUtil {

    public String updateStringToGetorSet(Field attribute) {
        return attribute.getName().replaceFirst(attribute.getName().substring(0, 1), attribute.getName().substring(0, 1).toUpperCase());
    }

    public void updateFieldsDtoToModel(Model oldObject, DTO newObject) {
        Field[] attributes = newObject.getClass().getDeclaredFields();
        for (Field attribute : attributes) {
            try {
                attribute.setAccessible(true);
                if (attribute.get(newObject) != null && attribute.getAnnotation(Type.class) == null) {
                    oldObject.getClass().getDeclaredMethod("set" + updateStringToGetorSet(attribute), attribute.getType()).invoke(oldObject, attribute.get(newObject));
                }else if(attribute.getAnnotation(Type.class) != null && attribute.get(newObject) != null) {
                    Object subModel = montaSubClasse(attribute.getAnnotation(Type.class).value(), attribute.get(newObject));
                    oldObject.getClass().getDeclaredMethod("set" + updateStringToGetorSet(attribute), attribute.getAnnotation(Type.class).value()).invoke(oldObject, subModel);
                }else if(attribute.getAnnotation(Type.class) == null) {
                    oldObject.getClass().getDeclaredMethod("set" + updateStringToGetorSet(attribute), attribute.getType()).invoke(oldObject, (Object) null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    public void updateFieldsModelToDTO(Model model, DTO dto) {
        Field[] attributes = model.getClass().getDeclaredFields();
        for (int i = 0; i < attributes.length - 5; i++) {
            try {
                attributes[i].setAccessible(true);
                if (attributes[i].get(model) != null) {
                    dto.getClass().getDeclaredMethod("set" + updateStringToGetorSet(attributes[i]), attributes[i].getType()).invoke(dto, attributes[i].get(model));
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    public HashMap createHashMap(Model model, DTO dto) {
        Field[] attributes = dto.getClass().getDeclaredFields();
        HashMap hashMap = new HashMap();
        for (Field field : attributes) {
            try {
                field.setAccessible(true);
                if (field.get(dto) != null) {
                    hashMap.put(field.getName(), field.get(dto));
                }
            } catch (Throwable t) {
                throw new RuntimeException();
            }
        }
        return hashMap;

    }

    private Object montaSubClasse(Class value, Object model) {
        try{
            Object dto = value.getDeclaredConstructor().newInstance();
            updateFieldsDtoToModel((Model) model, (DTO) dto);
            return dto;
        }catch (Throwable t){
            t.printStackTrace();
            ValidacaoException validacaoException = new ValidacaoException();
            validacaoException.add("Erro no sistema, favor informar o suporte");
            validacaoException.lancaErro();
            return null;
        }

    }
    public <T> Object objectMapperModelToDto(Object model,Object dto,Class<T> classeFinal ){
        try{
            Class<?> aClass = model.getClass();
            if(aClass != null){
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(model);
                    if(fieldValue != null){
                        dto.getClass().getDeclaredMethod("set" + updateStringToGetorSet(field),field.getType()).invoke(dto,fieldValue);
                    }

                }
            }
            return classeFinal.cast(dto);
        } catch (Throwable e){
            e.printStackTrace();
            return null;
        }
    }

    public void invokerExecutor(Invoker invoker){
        try{
            Method[] methods = invoker.getClass().getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                int length = method.getParameters().length;
                if(length == 0){
                    method.invoke(invoker);
                }
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }


    public <I,O> O objectMapper(I input){
        try{
            Class<?> source = input.getClass();
            Class<?> target = Class.forName(source.getName() + "DTO");

            O targetClass = (O) target.getDeclaredConstructor().newInstance();

            Field[] sourceFields = source.getDeclaredFields();
            Field[] targetFields = target.getDeclaredFields();

            Arrays.stream(sourceFields).forEach(sourceField ->
                    Arrays.stream(targetFields).forEach(targetField -> {
                        validate(sourceField, targetField);
                        try {
                            targetField.set(targetClass, sourceField.get(input));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }));

            return targetClass;
        }catch (Throwable t){
            t.printStackTrace();

        }
        throw new RuntimeException("Erro na conversão");
    }
    private void validate(Field sourceField, Field targetField) {
        if (sourceField.getName().equals(targetField.getName())
                && sourceField.getType().equals(targetField.getType())) {
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
        }
    }

}
