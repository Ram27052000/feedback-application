package com.ram.feedback_form.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "feedback_generator", sequenceName = "feedback_seq_generator", allocationSize = 1)
    private Integer id;

    private String name;

    private String email;

    private String feedback;

}
