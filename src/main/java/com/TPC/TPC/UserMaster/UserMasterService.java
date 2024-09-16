package com.TPC.TPC.UserMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMasterService {

    @Autowired
    private UserMasterRepository userMasterRepository;

    public Page<UserMaster> listarUserMasters(String userMaster, Pageable pageable) {
        if (userMaster != null) {
            return userMasterRepository.findByNome(userMaster, pageable);
        }
        return userMasterRepository.findAll(pageable);
    }

    public ResponseEntity<UserMaster> getUserMasterById(Integer masterID) {
        Optional<UserMaster> userMaster = userMasterRepository.findById(masterID);
        return userMaster.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<UserMaster> createUserMaster(UserMaster userMaster) {
        UserMaster savedUserMaster = userMasterRepository.save(userMaster);
        return new ResponseEntity<>(savedUserMaster, HttpStatus.CREATED);
    }

    public ResponseEntity<UserMaster> updateUserMaster(Integer masterID, UserMaster userMasterDetails) {
        return userMasterRepository.findById(masterID)
                .map(userMaster -> {
                    userMaster.setNome(userMasterDetails.getNome());
                    userMaster.setSobrenome(userMasterDetails.getSobrenome());
                    userMaster.setEmail(userMasterDetails.getEmail());
                    userMaster.setPassword(userMasterDetails.getPassword());
                    userMaster.setDataRegistro(userMasterDetails.getDataRegistro());
                    userMaster.setAtivo(userMasterDetails.getAtivo());
                    UserMaster updatedUserMaster = userMasterRepository.save(userMaster);
                    return ResponseEntity.ok(updatedUserMaster);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteUserMaster(Integer masterID) {
        return userMasterRepository.findById(masterID)
                .map(userMaster -> {
                    userMasterRepository.delete(userMaster);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
