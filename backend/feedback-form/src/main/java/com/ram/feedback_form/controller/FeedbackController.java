package com.ram.feedback_form.controller;

import com.ram.feedback_form.Model.Feedback;
import com.ram.feedback_form.Repository.FeedbackRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @PostMapping("/feedback")
    public void feedbackForm(@RequestBody Feedback feedback) {
      this.feedbackRepository.save(feedback);
    }

    @GetMapping("/get-feedback")
    public List<Feedback> getFeedback() {
        return this.feedbackRepository.findAll();
    }
}
