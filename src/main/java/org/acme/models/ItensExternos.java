package org.acme.models;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Nota_fiscal_eletronica.ImportacaoDados;
import org.acme.models.Nota_fiscal_eletronica.ImportacaoImposto;
import org.acme.models.enums.PrioridadeCarga;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
public class ItensExternos extends PanacheEntityBase implements Model, JsonSerializer<ItensExternos> {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL,fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Produto produto;
    private Long quantidade;

    @ManyToMany
    @JsonbTransient
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name="itens_pedido", joinColumns=
            {@JoinColumn(name="itens_id")}, inverseJoinColumns=
            {@JoinColumn(name="pedido_id")})
    private List<Pedido> pedido = new ArrayList<>();
    @ManyToMany(mappedBy = "itens")
    @JsonbTransient
    private Set<Compra> compras = new HashSet<>();
    private LocalDateTime ultimaAtualizacao;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private PrioridadeCarga prioridade;
    @OneToOne
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(nullable = true)
    @JsonbTransient
    private Loja loja;
    //Informações NFE
    @OneToOne
    private ImportacaoImposto importacao;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ImportacaoDados> importacaoDados;
    @OneToOne
    private Tributos tributos;
    @ManyToMany(mappedBy = "itens",fetch = FetchType.EAGER)
    @JsonbTransient
    private Set<EstoqueExterno> estoqueExternos;
    private String codigoBarrasTributavel;
    @Column(length =1000000)
    private String descricao;
    private String ncm;
    private String cest;
    private String cfop;

    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        ultimaAtualizacao = LocalDateTime.now();
    }

    @Override
    public JsonElement serialize(ItensExternos itensExternos, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(itensExternos);
    }
}
