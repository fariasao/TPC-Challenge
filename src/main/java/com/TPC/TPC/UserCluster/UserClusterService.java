package com.TPC.TPC.UserCluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserClusterService {

    @Autowired
    private UserClusterRepository userClusterRepository;

    public Page<UserCluster> listarUserClusters(String userCluster, Pageable pageable) {
        if (userCluster != null) {
            return userClusterRepository.findByUserClusterID(userCluster, pageable);
        }
        return userClusterRepository.findAll(pageable);
    }

    public ResponseEntity<UserCluster> getUserClusterById(Integer userClusterID) {
        Optional<UserCluster> userCluster = userClusterRepository.findById(userClusterID);
        return userCluster.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<UserCluster> createUserCluster(UserCluster userCluster) {
        UserCluster savedUserCluster = userClusterRepository.save(userCluster);
        return new ResponseEntity<>(savedUserCluster, HttpStatus.CREATED);
    }

    public ResponseEntity<UserCluster> updateUserCluster(Integer userClusterID, UserCluster userClusterDetails) {
        return userClusterRepository.findById(userClusterID)
                .map(userCluster -> {
                    userCluster.setClusterID(userClusterDetails.getClusterID());
                    userCluster.setUsersID(userClusterDetails.getUsersID());
                    UserCluster updatedUserCluster = userClusterRepository.save(userCluster);
                    return ResponseEntity.ok(updatedUserCluster);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteUserCluster(Integer userClusterID) {
        return userClusterRepository.findById(userClusterID)
                .map(userCluster -> {
                    userClusterRepository.delete(userCluster);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
