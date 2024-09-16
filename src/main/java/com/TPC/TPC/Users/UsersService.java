package com.TPC.TPC.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Page<Users> listarUsers(String user, Pageable pageable) {
        if (user != null) {
            return usersRepository.findByNome(user, pageable);
        }
        return usersRepository.findAll(pageable);
    }

    public Optional<Users> getUserById(Integer usersID) {
        return usersRepository.findById(usersID);
    }

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    public Optional<Users> updateUser(Integer usersID, Users userDetails) {
        return usersRepository.findById(usersID).map(user -> {
            user.setNome(userDetails.getNome());
            user.setSobrenome(userDetails.getSobrenome());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setTelefone(userDetails.getTelefone());
            user.setEndereco(userDetails.getEndereco());
            user.setNumero(userDetails.getNumero());
            user.setComplemento(userDetails.getComplemento());
            user.setAtivo(userDetails.getAtivo());
            return usersRepository.save(user);
        });
    }

    public boolean deleteUser(Integer usersID) {
        return usersRepository.findById(usersID).map(user -> {
            usersRepository.delete(user);
            return true;
        }).orElse(false);
    }
}
