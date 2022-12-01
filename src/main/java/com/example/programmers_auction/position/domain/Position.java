package com.example.programmers_auction.position.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "positions")
@NoArgsConstructor(access = PROTECTED)
public class Position {

    @Column(name = "position_id")
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, name = "position_name")
    private String positionName;

    public Position(String positionName) {
        this.positionName = positionName;
    }
}
