package org.acme.ModelsByTest;

import lombok.Getter;
import lombok.Setter;
import org.acme.ModelsByTest.enums.PrioridadeCarga;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Itens {
    private String uuid;
    private Produto produto;
    private Long quantidade;

    private List<Pedido> pedido = new ArrayList<>();
    private Set<Compra> compras = new HashSet<>();
    private LocalDateTime ultimaAtualizacao;
    private LocalDateTime dataCriacao;
    private PrioridadeCarga prioridade;
    private Loja loja;
    //Informações NFE
    private String codigoBarrasTributavel;
    private String descricao;
    private String ncm;
    private String cest;
    private String cfop;
}
