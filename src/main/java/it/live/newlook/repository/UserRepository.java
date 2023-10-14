package it.live.newlook.repository;

import it.live.newlook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByPhoneNumberAndUsername(String phoneNumber, String username);
}
