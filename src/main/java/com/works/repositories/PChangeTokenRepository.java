package com.works.repositories;

import com.works.entities.PasswordChangeToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PChangeTokenRepository extends JpaRepository<PasswordChangeToken, Integer> {
    PasswordChangeToken findByTokenEquals(String token);

}
