package br.com.fiap.gameapi.controller;

import br.com.fiap.gameapi.dto.Request.UsuarioRequest;
import br.com.fiap.gameapi.dto.Response.MensagemResponse;
import br.com.fiap.gameapi.dto.Response.UsuarioResponse;
import br.com.fiap.gameapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<UsuarioResponse>>> listar() {
        List<EntityModel<UsuarioResponse>> usuarios = service.listar()
                .stream()
                .map(this::adicionarLinks)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(
                        usuarios,
                        linkTo(methodOn(UsuarioController.class).listar()).withSelfRel()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResponse>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                adicionarLinks(service.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<MensagemResponse> criar(@RequestBody @Valid UsuarioRequest request) {
        return ResponseEntity.status(201).body(service.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemResponse> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    private EntityModel<UsuarioResponse> adicionarLinks(UsuarioResponse usuario) {
        return EntityModel.of(
                usuario,
                linkTo(methodOn(UsuarioController.class).buscarPorId(usuario.id())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listar()).withRel("listar-usuarios")
        );
    }
}