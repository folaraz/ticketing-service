package com.parking.ticket.model.db;


import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private com.parking.ticket.model.Role name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.parking.ticket.model.Role getName() {
        return name;
    }

    public void setName(com.parking.ticket.model.Role name) {
        this.name = name;
    }
}
