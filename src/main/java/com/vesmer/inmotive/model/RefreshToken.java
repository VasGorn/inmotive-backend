package com.vesmer.inmotive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Refresh token is required")
    @Column(nullable = false)
    private String token;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private Instant created;
}
