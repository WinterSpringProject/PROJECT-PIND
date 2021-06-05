package io.spring.pind.dto;

import io.spring.pind.entity.Member;
import io.spring.pind.entity.Participate;
import io.spring.pind.entity.ParticipateRole;
import io.spring.pind.entity.Project;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipateDTO {

    private Long id;
    private Long member_id;
    private String member_email;
    private Long project_id;
    private String project_title;
    private ParticipateRole role;

    public static ParticipateDTO entityToDto(Participate participate){

        Project project = participate.getProject();
        Member member = participate.getMember();

        ParticipateDTO participateDTO = ParticipateDTO.builder()
                .id(participate.getId())
                .member_id(member.getId())
                .member_email(member.getEmail())
                .project_id(project.getId())
                .project_title(project.getTitle())
                .role(participate.getRole())
                .build();

        return participateDTO;
    }
}
