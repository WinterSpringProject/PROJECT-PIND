package io.spring.pind.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;

    // private Long region_id;

    private String email;

    private String name;

    private String password;

    private String certifiedKey;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
