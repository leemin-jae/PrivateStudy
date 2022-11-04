package com.ssafy.soldsolve.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.soldsolve.entity.EmailToken;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {
	Optional<EmailToken> findByIdAndExpirationDateAfterAndExpired(String emailTokenId, LocalDateTime now, boolean expired);
}