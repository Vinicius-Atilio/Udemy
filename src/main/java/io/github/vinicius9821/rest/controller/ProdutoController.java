package io.github.vinicius9821.rest.controller;

import io.github.vinicius9821.domain.repository.Produtos;
import io.github.vinicius9821.domain.entity.Cliente;
import io.github.vinicius9821.domain.entity.Produto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos repository;

    public ProdutoController(Produtos repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto){
        return repository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid Produto produto){
        repository
                .findById(id)
                .map(p ->{
                    produto.setId(p.getId());
                    repository.save(produto);
                    return ResponseEntity.noContent().build();
                }).orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto nao encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.findById(id)
                .map( p->{
                    repository.delete(p);
                    return Void.TYPE;
                        })
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto nao encontrado"));
    }

    @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id ) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto nao encontrado"));
    }
    @GetMapping
    public List<Cliente> find(Produto filtro ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }

}
