package org.formation.domain;

import lombok.Data;

@Data
public class Transition {
    String action;
    String sourceState;
    String targetState;
}
