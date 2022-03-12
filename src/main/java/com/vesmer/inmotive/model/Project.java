package com.vesmer.inmotive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Project name is required")
    @Column(nullable = false, length = 63)
    private String name;

    private String description;

    @Column(nullable = false)
    private double supplyVoltage;

    @Column(nullable = false)
    private double supplyFrequency;

    @DecimalMin(value = "0.2", message = "Slip must be greater then 0.2")
    @DecimalMax(value = "1.0", message = "Slip value must less then 1.0")
    @Column(nullable = false, name="max_slip", columnDefinition = "double " +
            "precision CHECK (max_slip >= 0.2 AND max_slip <= 1.0)")
    private double maxSlip;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant created;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            foreignKey = @ForeignKey(name = "user_id_fk"),
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

}
