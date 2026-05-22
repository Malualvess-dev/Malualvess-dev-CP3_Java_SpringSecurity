package br.com.fiap.gameapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "TB_PLATAFORMA")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLATAFORMA")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "FABRICANTE", nullable = false)
    private String fabricante;

    @OneToMany(mappedBy = "plataforma")
    @JsonIgnore
    private List<Jogo> jogos;

    //GET e SET


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }
}