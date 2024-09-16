package com.TPC.TPC.UserPDV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPDVService {

    @Autowired
    private UserPDVRepository userPDVRepository;

    public Page<UserPDV> listarUserPDV(String userPDV, Pageable pageable) {
        if (userPDV != null) {
            return userPDVRepository.findByNome(userPDV, pageable);
        }
        return userPDVRepository.findAll(pageable);
    }

    public ResponseEntity<UserPDV> getUserPDVById(Integer userPdvID) {
        Optional<UserPDV> userPDV = userPDVRepository.findById(userPdvID);
        return userPDV.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<UserPDV> createUserPDV(UserPDV userPDV) {
        UserPDV savedUserPDV = userPDVRepository.save(userPDV);
        return new ResponseEntity<>(savedUserPDV, HttpStatus.CREATED);
    }

    public ResponseEntity<UserPDV> updateUserPDV(Integer userPdvID, UserPDV userPDVDetails) {
        return userPDVRepository.findById(userPdvID)
                .map(userPDV -> {
                    userPDV.setPdvID(userPDVDetails.getPdvID());
                    userPDV.setNome(userPDVDetails.getNome());
                    userPDV.setSobrenome(userPDVDetails.getSobrenome());
                    userPDV.setEmail(userPDVDetails.getEmail());
                    userPDV.setPassword(userPDVDetails.getPassword());
                    userPDV.setDataRegistro(userPDVDetails.getDataRegistro());
                    userPDV.setAtivo(userPDVDetails.getAtivo());
                    UserPDV updatedUserPDV = userPDVRepository.save(userPDV);
                    return ResponseEntity.ok(updatedUserPDV);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteUserPDV(Integer userPdvID) {
        return userPDVRepository.findById(userPdvID)
                .map(userPDV -> {
                    userPDVRepository.delete(userPDV);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
