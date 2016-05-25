package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Atividades")
public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAtividade;
    @Type(type="text")
    @Column(nullable = true)
    private String ementa;
    @Column(nullable = true, length = 15)
    private String tipo;
    @Column(nullable = true, length = 15)
    private String tipoMocao;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;    
    
    @PrePersist
    protected void onCreate() {
        dataAtividade = new Date();
    }
    
   @PreUpdate
    protected void onUpdate() {
        dataAlteracao = new Date();
    }
    
    public Atividade() {}

    public Atividade(String ementa, String tipo, String tipoMocao, Usuario usuario) {
        this.ementa = ementa;
        this.tipo = tipo;
        this.tipoMocao = tipoMocao;
        this.usuario = usuario;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoMocao() {
        return tipoMocao;
    }

    public void setTipoMocao(String tipoMocao) {
        this.tipoMocao = tipoMocao;
    }

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getDataAtividade() {
        return dataAtividade;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
