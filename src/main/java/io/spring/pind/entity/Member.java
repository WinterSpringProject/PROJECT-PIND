package io.spring.pind.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"region"})
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private FromSocial fromSocial;

    @Column
    private String certifiedKey;

    public void changeEmail(String email){
        this.email = email;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void changeCertifiedKey(String certifiedKey) {
        this.certifiedKey = certifiedKey;
    }

}
