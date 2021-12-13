package com.example.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
public interface Repository extends JpaRepository<User, Integer> {
}
