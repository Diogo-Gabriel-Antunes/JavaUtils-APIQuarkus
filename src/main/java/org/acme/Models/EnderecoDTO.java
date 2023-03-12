package org.acme.Models;

import lombok.Getter;
import lombok.Setter;
import org.acme.Models.Enum.Estado;
import org.acme.Models.Enum.TipoBairro;
import org.acme.Models.Enum.TipoLogradouro;
import org.acme.Util.InterfacesUtil.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class EnderecoDTO implements DTO {
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
