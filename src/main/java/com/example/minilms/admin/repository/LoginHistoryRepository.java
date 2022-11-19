package com.example.minilms.admin.repository;

import com.example.minilms.admin.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    Optional<List<LoginHistory>> findAllByUserIdOrderByLoginDtDesc(String userId);
}
