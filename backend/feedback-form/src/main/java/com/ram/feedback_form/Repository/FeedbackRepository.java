package com.ram.feedback_form.Repository;

import com.ram.feedback_form.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
