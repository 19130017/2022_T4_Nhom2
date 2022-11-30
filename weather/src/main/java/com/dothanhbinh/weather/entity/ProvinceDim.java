package com.dothanhbinh.weather.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "province_dim")
public class ProvinceDim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code_name")
    private String code_name;

    @OneToMany(mappedBy = "province")
    Set<FactWeather> provinces;
}
