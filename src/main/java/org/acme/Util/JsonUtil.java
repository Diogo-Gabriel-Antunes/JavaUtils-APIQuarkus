package org.acme.Util;

import com.google.gson.Gson;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.Exception.UtilException;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.models.Venda;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class JsonUtil {
    public static void main(String[] args) {
        try {
            Gson gson = new GsonUtil().parser;
            HashMap hashMap = gson.fromJson(" {\n" +
                    "        \"CNH\": \"Duis\",\n" +
                    "        \"CPF\": \"Etiam gravida molestie\",\n" +
                    "        \"categoriaCNH\": \"c\",\n" +
                    "        \"dataAtualizacao\": \"2023-03-07T00:00:00\",\n" +
                    "        \"dataCriacao\": \"2023-03-07T00:00:00\",\n" +
                    "        \"dataDeValidadeCNH\": \"2024-01-10\",\n" +
                    "        \"email\": \"curabitur.sed.tortor@hotmail.org\",\n" +
                    "        \"endereco\": {\n" +
                    "            \"bairro\": \"Västra Götalands län\",\n" +
                    "            \"cep\": \"886922\",\n" +
                    "            \"codigoCidade\": \"Ukraine\",\n" +
                    "            \"codigoPais\": \"83-88\",\n" +
                    "            \"complemento\": \"Kennan England\",\n" +
                    "            \"descricaoCidade\": \"dis parturient\",\n" +
                    "            \"descricaoPais\": \"Austin Robles\",\n" +
                    "            \"estado\": \"SC\",\n" +
                    "            \"logradouro\": \"460 Velit St.\",\n" +
                    "            \"numero\": \"6\",\n" +
                    "            \"tipoBairro\": \"Bairro\",\n" +
                    "            \"tipoLogradouro\": \"Rua\",\n" +
                    "            \"uuid\": \"9BE473AE-B9B5-5DC9-0F3B-6AEA2A729378\"\n" +
                    "        },\n" +
                    "        \"idade\": 10,\n" +
                    "        \"nome\": \"Diogo\",\n" +
                    "        \"sobrenome\": \"Antunes\",\n" +
                    "        \"telefone\": \"(38)31625-7316\",\n" +
                    "        \"uuid\": \"7BC9BEB7-BBB2-1234-0165-567BE6C2FB98\"\n" +
                    "    }", HashMap.class);
            hashMap.forEach((key, value) -> {
                if (!StringUtil.stringValida(String.valueOf(value))) {
                    UtilException validacao = new UtilException();
                    validacao.add("Existem campos importantes vazios que não conseguimos identificar");
                    validacao.add("Caso o erro persistir favor entrar em contato com o suporte");
                    validacao.lancaErro();
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static String preValidate(String json, Class classe) {
        UtilException validacao = new UtilException();
        Gson gson = new GsonUtil().parser;
        try {
            HashMap hashMap = gson.fromJson(json, HashMap.class);
            hashMap.forEach((key, value) -> {
                if (!StringUtil.stringValida(String.valueOf(value))) {
                    try {
                        Field field = classe.getDeclaredField(String.valueOf(key));
                        if (field.getAnnotation(Type.class) != null && field.getAnnotation(Type.class).value() != null) {
                            atributoTipoClasse(field.getAnnotation(Type.class).value(), gson.toJson(value));
                        } else {
                            if (field.getAnnotation(LabelForm.class) != null && StringUtil.stringValida(field.getAnnotation(LabelForm.class).value())) {
                                String label = field.getAnnotation(LabelForm.class).value();
                                validacao.add("Campo " + label + " Esta com algum problema por favor verificar");
                                hashMap.remove(key);
                            } else {
                                validacao.add("Existe algum campo invalido que não foi possivel identificar");
                                hashMap.remove(key);
                            }
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                        validacao.add("Existem campos importantes vazios que não conseguimos identificar");
                        hashMap.remove(key);
                    }
                }
            });
            validacao.add("Caso o erro persistir favor entrar em contato com o suporte");
            return gson.toJson(hashMap);
        } catch (Throwable t) {
            t.printStackTrace();
            validacao.add("Caso o erro persistir favor entrar em contato com o suporte");
            return gson.toJson(validacao.getErro());
        }
    }

    public static String preValidateFilter(String json) {
        Gson gson = new GsonUtil().parser;
        ConcurrentHashMap hashMap = gson.fromJson(json, ConcurrentHashMap.class);

        novoHashMapValidado(hashMap, gson);
        return gson.toJson(hashMap);
    }

    private static void novoHashMapValidado(ConcurrentHashMap hashMap, Gson gson) {
        hashMap.forEach((key, value) -> {
            if (!StringUtil.stringValida(String.valueOf(value))) {
                hashMap.remove(key);
            } else if (StringUtil.stringValida(String.valueOf(value))) {
                String jsonSubClass = gson.toJson(value);
                if (jsonSubClass.contains("{") && jsonSubClass.contains("}") && jsonSubClass.contains("[") && jsonSubClass.contains("]")) {
                    Object[] objects = gson.fromJson(jsonSubClass, Object[].class);
                    for (Object object : objects) {
                        String jsonObject = gson.toJson(object);
                        ConcurrentHashMap concurrentHashMap = gson.fromJson(jsonObject, ConcurrentHashMap.class);
                        String retorno = validateSubClass(concurrentHashMap);
                        validaAndRemoveAndReplacaObjectAndList(concurrentHashMap,key,retorno);
                    }
                } else if (jsonSubClass.contains("{") && jsonSubClass.contains("}")) {
                    ConcurrentHashMap retorno = gson.fromJson(jsonSubClass, ConcurrentHashMap.class);
                    if (retorno.size() >= 2) {
                        String newSubClass = validateSubClass(retorno);
                        validaAndRemoveAndReplacaObjectAndList(hashMap, key, newSubClass);
                    }
                    ;
                }
            }
        });
    }

    private static void validaAndRemoveAndReplacaObjectAndList(ConcurrentHashMap hashMap, Object key, String retorno) {
        if (StringUtil.validaStringAposValidadeSubClass(retorno)) {
            hashMap.remove(key);
        } else {
            hashMap.replace(key, retorno);
        }
    }

    private static String validateSubClass(ConcurrentHashMap hashMap) {
        Gson gson = new GsonUtil().parser;
        novoHashMapValidado(hashMap, gson);
        return gson.toJson(hashMap);
    }

    private static void atributoTipoClasse(Class value, String json) {
        preValidate(json, value);
    }
}


