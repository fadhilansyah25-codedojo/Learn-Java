// package com.pembekalan.xsisacademy.repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

// import com.pembekalan.xsisacademy.entity.User;

// @Repository
// public interface UserRepository extends JpaRepository<User, Integer> {
//     @Query(value = "SELECT u FROM User u ORDER BY u.updatedAt DESC")
//     List<User> getAllUsers();
// }
