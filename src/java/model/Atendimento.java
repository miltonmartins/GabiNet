package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Atendimentos")
public class Atendimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAtendimento;
    @Type(type="text")
    @Column(nullable = true)
    private String solicitacao;
    @Type(type="text")
    @Column(nullable = true)
    private String providencia;
    @Type(type="text")
    @Column(nullable = true)
    private String retorno;
    @ManyToOne
    @JoinColumn(nullable = false, name = "idUsuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(nullable = false, name = "cpfCidadao")
    private Cidadao cidadao;
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;
    
    public Atendimento() {}

    public Atendimento(String solicitacao, String providencia, Usuario usuario, Cidadao cidadao) {
        this.solicitacao = solicitacao;
        this.providencia = providencia;
        this.usuario = usuario;
        this.cidadao = cidadao;
    }
    
    @PrePersist
    protected void onCreate() {
        dataAtendimento = new Date();
        //dataCriacao = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dataAlteracao = new Date();
    }
    
    public String getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(String solicitacao) {
        this.solicitacao = solicitacao;
    }

    public String getProvidencia() {
        return providencia;
    }

    public void setProvidencia(String providencia) {
        this.providencia = providencia;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public long getId() {
        return id;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }
    
}