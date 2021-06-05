package io.spring.pind.service;

import io.spring.pind.dto.ParticipateDTO;

import java.util.List;

public interface ParticipateService {

    List<ParticipateDTO> getParticipateList(Long project_id);

    ParticipateDTO enroll(Long project_id, Long member_id);

    Boolean acceptMember(Long project_id, Long member_id);

    Boolean deleteMember(Long project_id, Long member_id);

}
