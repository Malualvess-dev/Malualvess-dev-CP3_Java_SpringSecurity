package br.com.fiap.gameapi.model;

import jakarta.persistence.*;


@Entity
@Table(name = "TB_JOGO")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_JOGO")
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "GENERO", nullable = false)
    private String genero;

    @Column(name = "ANO_LANCAMENTO", nullable = false)
    private Integer anoLancamento;

    @Column(name = "PRECO", nullable = false)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "ID_PLATAFORMA")
    private Plataforma plataforma;

    //GET e SET


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }
}