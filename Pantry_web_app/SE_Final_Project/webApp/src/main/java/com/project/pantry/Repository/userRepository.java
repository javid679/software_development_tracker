package com.project.pantry.Repository;
// THis is User Repository which finding user by email for Login and Signup Controller Requests.
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pantry.model.User;

public interface userRepository extends JpaRepository<User,Long> {
	Optional<User> findByEmail(String email);


}
