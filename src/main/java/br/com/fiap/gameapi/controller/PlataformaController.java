package br.com.fiap.gameapi.controller;

import br.com.fiap.gameapi.dto.Request.PlataformaRequest;
import br.com.fiap.gameapi.dto.Response.MensagemResponse;
import br.com.fiap.gameapi.dto.Response.PlataformaResponse;
import br.com.fiap.gameapi.service.PlataformaService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/plataformas")
public class PlataformaController {

    private final PlataformaService service;

    public PlataformaController(PlataformaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<PlataformaResponse>>> listar() {
        List<EntityModel<PlataformaResponse>> plataformas = service.listar()
                .stream()
                .map(this::adicionarLinks)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(
                        plataformas,
                        linkTo(methodOn(PlataformaController.class).listar()).withSelfRel()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PlataformaResponse>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                adicionarLinks(service.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<MensagemResponse> criar(@RequestBody @Valid PlataformaRequest request) {
        return ResponseEntity.status(201).body(service.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PlataformaRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemResponse> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    private EntityModel<PlataformaResponse> adicionarLinks(PlataformaResponse plataforma) {
        return EntityModel.of(
                plataforma,
                linkTo(methodOn(PlataformaController.class).buscarPorId(plataforma.id())).withSelfRel(),
                linkTo(methodOn(PlataformaController.class).listar()).withRel("listar-plataformas")
        );
    }
}