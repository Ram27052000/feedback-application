import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {FeedbackForm} from "./models/feedbackform";
import {FeedbackService} from "./shared/feedback.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['./feedback-form.component.scss']
})
export class FeedbackFormComponent implements OnInit {

  user: FeedbackForm = {
    name : '',
    email : '',
    feedback : ''
  }
  submitted = false;
  constructor(private feedbackService: FeedbackService,private snackbar: MatSnackBar) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.submitted = true;
    if(form.invalid){
        return;
    }
    this.feedbackService.submitFeedbackForm(form.value).subscribe({
      next:() =>{
        form.reset();
        this.submitted = false;
        this.snackbar.open("Feedback Submitted Successfully", "OK");
      },
      error:(err) =>{
        console.log(err);
      }
    })
  }
}
