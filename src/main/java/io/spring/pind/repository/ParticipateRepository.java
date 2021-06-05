package io.spring.pind.repository;

import io.spring.pind.entity.Participate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipateRepository extends JpaRepository<Participate, Long> {

    @EntityGraph(attributePaths = {"member", "project"})
    Optional<Participate> findByProjectIdAndMemberId(Long project_id, Long member_id);

    @EntityGraph(attributePaths = {"member", "project"})
    List<Participate> findByProjectId(Long project_id);
}
