package com.TPC.TPC.controller;

import com.TPC.TPC.model.Users;
import com.TPC.TPC.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    // Buscar todos os usuários
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Buscar um usuário pelo ID
    @GetMapping("/{usersID}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer usersID) {
        return usersRepository.findById(usersID)
                .map(user -> ResponseEntity.ok(user))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo usuário
    @PostMapping
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
        Users savedUser = usersRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Atualizar um usuário existente
    @PutMapping("/{usersID}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer usersID, @Valid @RequestBody Users userDetails) {
        return usersRepository.findById(usersID)
                .map(user -> {
                    user.setNome(userDetails.getNome());
                    user.setSobrenome(userDetails.getSobrenome());
                    user.setEmail(userDetails.getEmail());
                    user.setPassword(userDetails.getPassword());
                    user.setTelefone(userDetails.getTelefone());
                    user.setEndereco(userDetails.getEndereco());
                    user.setNumero(userDetails.getNumero());
                    user.setComplemento(userDetails.getComplemento());
                    user.setAtivo(userDetails.isAtivo());
                    final Users updatedUser = usersRepository.save(user);
                    return ResponseEntity.ok(updatedUser);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um usuário
    @DeleteMapping("/{usersID}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer usersID) {
        return usersRepository.findById(usersID)
                .map(user -> {
                    usersRepository.delete(user);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
