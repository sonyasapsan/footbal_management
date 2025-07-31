package com.project.football.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;
    @OneToOne(optional = false)
    @JoinColumn(name = "from_team_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team fromTeam;
    @OneToOne(optional = false)
    @JoinColumn(name = "to_team_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team toTeam;
    @Column(nullable = false)
    private BigDecimal commission;
    @Column(nullable = false)
    private BigDecimal totalCost;
    @Column(nullable = false)
    private BigDecimal transferAmount;
}
