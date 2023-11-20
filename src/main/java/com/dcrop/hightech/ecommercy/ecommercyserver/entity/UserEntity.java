package com.dcrop.hightech.ecommercy.ecommercyserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(length = 300)
    String username;

    @Column(length = 300)
    String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<AuthorityEntity> authorities = new ArrayList<>();

    public void setRoleDefault() {
        AuthorityEntity roleDefault = new AuthorityEntity();
        roleDefault.setName("MEMBER");

        authorities.add(roleDefault);
    }

}
