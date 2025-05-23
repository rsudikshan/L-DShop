package com.sr.L.DShop.repo;

import com.sr.L.DShop.entities.LdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<LdUser,Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<LdUser> findByUsername(String username);
}
