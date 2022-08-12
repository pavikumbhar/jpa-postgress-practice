package com.pavikumbhar.jpa.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="OPERATING_SYSTEM")
@FieldNameConstants(asEnum = true)
public class OperatingSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "VERSION", nullable = false)
    private String version;

    @Column(name = "KERNEL", nullable = false)
    private String kernel;

    @Column(name = "RELEASE_DATE", nullable = false)
    private LocalDateTime releaseDate;

    @Column(name = "USAGES", nullable = false)
    private int usages;

}