package org.acme.ModelsByTest;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ClassDTO implements DTO {
    private String uuid;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private String CPF;
    private String CNH;
    private String categoriaCNH;
    private LocalDate dataDeValidadeCNH;
    private String telefone;
    private String email;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
