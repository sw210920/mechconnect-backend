package com.mechconnect.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechconnect.backend.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Long>  {

}
