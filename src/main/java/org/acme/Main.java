package org.acme;

import org.acme.Util.JsonUtil;

public class Main {
    public static void main(String[] args) {
        String json = "{\n" +
                "    \"itens\": [\n" +
                "        {\n" +
                "            \"produto\": {\n" +
                "                \"categoria\": {\n" +
                "                    \"nome\": \"teste\",\n" +
                "                    \"uuid\": \"ff80818186469fed018646c7830d0003\"\n" +
                "                },\n" +
                "                \"codigo\": \"teste444\",\n" +
                "                \"dataAlteracao\": \"2023-02-17T00:00:00\",\n" +
                "                \"dataCriacao\": \"2023-02-17T00:00:00\",\n" +
                "                \"nome\": \"teste444\",\n" +
                "                \"pesoBruto\": 2,\n" +
                "                \"pesoCubico\": 450,\n" +
                "                \"precoUnitario\": 2,\n" +
                "                \"quantidadeMinima\": 2,\n" +
                "                \"status\": false,\n" +
                "                \"uuid\": \"ff8081818661b7bb018661c4973f000a\"\n" +
                "            },\n" +
                "            \"quantidade\": 0,\n" +
                "            \"ultimaAtualizacao\": \"2023-02-21T01:05:04.979222\",\n" +
                "            \"uuid\": \"ff8081818661b7bb018661c4973f000b\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"transportador\": {\n" +
                "        \"cnpjCpf\": \"teste\",\n" +
                "        \"endereco\": {\n" +
                "            \"bairro\": \"teste\",\n" +
                "            \"cep\": \"teste\",\n" +
                "            \"complemento\": \"teste\",\n" +
                "            \"descricaoCidade\": \"TESTE\",\n" +
                "            \"descricaoPais\": \"teste\",\n" +
                "            \"estado\": \"SC\",\n" +
                "            \"logradouro\": \"teste\",\n" +
                "            \"numero\": \"2\",\n" +
                "            \"uuid\": \"ff80818186b434200186b43449470000\"\n" +
                "        },\n" +
                "        \"inscricaoEstadual\": \"teste\",\n" +
                "        \"nome\": \"teste\",\n" +
                "        \"razaoSocial\": \"teste\",\n" +
                "        \"uuid\": \"ff80818186b434200186b43449550001\"\n" +
                "    },\n" +
                "    \"motorista\": {\n" +
                "        \"CNH\": \"molestie\",\n" +
                "        \"CPF\": \"lectus sit amet\",\n" +
                "        \"categoriaCNH\": \"b\",\n" +
                "        \"dataAtualizacao\": \"2023-03-07T00:00:00\",\n" +
                "        \"dataCriacao\": \"2023-03-07T00:00:00\",\n" +
                "        \"dataDeValidadeCNH\": \"2022-05-12\",\n" +
                "        \"email\": \"praesent.eu@hotmail.org\",\n" +
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
                "        \"idade\": 1,\n" +
                "        \"nome\": \"Diogo\",\n" +
                "        \"sobrenome\": \"Antunes\",\n" +
                "        \"telefone\": \"(05)39867-7174\",\n" +
                "        \"uuid\": \"D5A5C64F-1638-5456-C845-4D27B99439BC\"\n" +
                "    },\n" +
                "    \"veiculo\": {\n" +
                "        \"anoFabricacao\": 2000,\n" +
                "        \"anoModelo\": 2000,\n" +
                "        \"capacidadeTracao\": 2,\n" +
                "        \"chassi\": \"2\",\n" +
                "        \"codigoCorDenatran\": 2,\n" +
                "        \"codigoModelo\": \"2\",\n" +
                "        \"condicao\": 2,\n" +
                "        \"cor\": {\n" +
                "            \"codigoCor\": \"06\",\n" +
                "            \"descricaoCor\": \"DOURADA\",\n" +
                "            \"uuid\": \"ff8081818604ae0a01860593dfba0007\"\n" +
                "        },\n" +
                "        \"lotacaoMaxima\": 2000,\n" +
                "        \"pesoBruto\": 0,\n" +
                "        \"placa\": \"2002\",\n" +
                "        \"potenciaMotor\": 0,\n" +
                "        \"restricao\": 0,\n" +
                "        \"tipo\": 0,\n" +
                "        \"tipoCombustivel\": 0,\n" +
                "        \"tipoOperacao\": 0,\n" +
                "        \"uuid\": \"ff80818186af6def0186afc7a787000b\"\n" +
                "    }\n" +
                "}";

        String teste = JsonUtil.preValidateFilter(json);
        System.out.println(teste);
    }
}
