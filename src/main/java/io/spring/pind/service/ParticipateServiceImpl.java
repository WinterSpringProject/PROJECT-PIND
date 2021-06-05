package io.spring.pind.service;

import io.spring.pind.dto.ParticipateDTO;
import io.spring.pind.entity.Member;
import io.spring.pind.entity.Participate;
import io.spring.pind.entity.ParticipateRole;
import io.spring.pind.entity.Project;
import io.spring.pind.repository.MemberRepository;
import io.spring.pind.repository.ParticipateRepository;
import io.spring.pind.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ParticipateServiceImpl implements ParticipateService{

    private final ParticipateRepository participateRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ParticipateDTO> getParticipateList(Long project_id) {
        List<Participate> result = participateRepository.findByProjectId(project_id);
        return result.stream().map(ParticipateDTO::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ParticipateDTO enroll(Long project_id, Long member_id) {
        Optional<Participate> result = participateRepository.findByProjectIdAndMemberId(project_id, member_id);

        if (result.isEmpty()){
            Optional<Project> resultProject = projectRepository.findById(project_id);
            Optional<Member> resultMember = memberRepository.findById(member_id);

            if (resultProject.isPresent() && resultMember.isPresent()){
                Project project = resultProject.get();
                Member member = resultMember.get();

                Participate newParticipate = Participate.builder()
                        .member(member)
                        .project(project)
                        .role(ParticipateRole.GUEST).build();

                participateRepository.save(newParticipate);

                return ParticipateDTO.entityToDto(newParticipate);
            }
        }
        return null;
    }

    @Transactional
    @Override
    public Boolean acceptMember(Long project_id, Long member_id) {
        Optional<Participate> result = participateRepository.findByProjectIdAndMemberId(project_id, member_id);

        if (result.isPresent()){
            Participate participate = result.get();
            if (participate.getRole() == ParticipateRole.GUEST){
                participate.changeRole(ParticipateRole.MEMBER);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteMember(Long project_id, Long member_id) {
        Optional<Participate> result = participateRepository.findByProjectIdAndMemberId(project_id, member_id);

        if (result.isPresent()){
            Participate participate = result.get();
            if (participate.getRole() != ParticipateRole.LEADER) {
                participateRepository.deleteById(participate.getId());
                return true;
            }
        }
        return false;
    }
}
