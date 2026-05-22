package br.com.fiap.gameapi.model;

import jakarta.persistence.*;


@Entity
@Table(name = "TB_USUARIO")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "ROLE", nullable = false)
    private String role;

    //GET e SET


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}