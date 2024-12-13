package org.example.repository;

import org.example.entitys.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
}
