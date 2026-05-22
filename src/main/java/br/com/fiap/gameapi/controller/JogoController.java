package br.com.fiap.gameapi.controller;

import br.com.fiap.gameapi.dto.Request.JogoRequest;
import br.com.fiap.gameapi.dto.Response.JogoResponse;
import br.com.fiap.gameapi.dto.Response.MensagemResponse;
import br.com.fiap.gameapi.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    private final JogoService service;

    public JogoController(JogoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<JogoResponse>>> listar() {
        List<EntityModel<JogoResponse>> jogos = service.listar()
                .stream()
                .map(this::adicionarLinks)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(
                        jogos,
                        linkTo(methodOn(JogoController.class).listar()).withSelfRel()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<JogoResponse>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                adicionarLinks(service.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<MensagemResponse> criar(@RequestBody @Valid JogoRequest request) {
        return ResponseEntity.status(201).body(service.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid JogoRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemResponse> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    private EntityModel<JogoResponse> adicionarLinks(JogoResponse jogo) {
        return EntityModel.of(
                jogo,
                linkTo(methodOn(JogoController.class).buscarPorId(jogo.id())).withSelfRel(),
                linkTo(methodOn(JogoController.class).listar()).withRel("listar-jogos"),
                linkTo(methodOn(PlataformaController.class).buscarPorId(jogo.plataforma().id())).withRel("plataforma")
        );
    }
}