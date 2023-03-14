package org.acme.models;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Enum.Estado;
import org.acme.models.Enum.TipoBairro;
import org.acme.models.Enum.TipoLogradouro;
import org.acme.Util.InterfacesUtil.Model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class Endereco implements Model {
    private String uuid;
    private String bairro;
    private String cep;
    private String codigoCidade;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String logradouro;
    private String numero;
    @Enumerated(EnumType.STRING)
    private TipoLogradouro tipoLogradouro;
    private String codigoPais;
    private String complemento;
    private String descricaoCidade;
    private String descricaoPais;
    @Enumerated(EnumType.STRING)
    private TipoBairro tipoBairro;
}
