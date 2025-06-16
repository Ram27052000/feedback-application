import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {FeedbackForm} from "./models/feedbackform";
import {FeedbackService} from "./shared/feedback.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['./feedback-form.component.scss']
})
export class FeedbackFormComponent implements OnInit {

  feedbackForm!: FormGroup;
  submitted = false;

  constructor(private feedbackService: FeedbackService, private snackbar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.feedbackForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      feedback: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(50)]),
    });
  }

  onSubmit(form: FormGroup): void {
    this.submitted = true;
    if(form.invalid){
      return;
    }
    this.feedbackService.submitFeedbackForm(form.value).subscribe({
      next: () => {
        form.reset();
        this.snackbar.open('Feedback Submitted Successfully', "OK");
      },
      error:() =>{
        this.snackbar.open('Feedback Failed', "OK");
    }
    })
  }
}
  // Template-form
  //
  // user: FeedbackForm = {
  //   name : '',
  //   email : '',
  //   feedback : ''
  // }
  // submitted = false;
  // onSubmit(form: NgForm) {
  //   this.submitted = true;
  //   if(form.invalid){
  //     return;
  //   }
  //   this.feedbackService.submitFeedbackForm(form.value).subscribe({
  //     next:() =>{
  //       form.reset();
  //       this.submitted = false;
  //       this.snackbar.open("Feedback Submitted Successfully", "OK");
  //     },
  //     error:(err) =>{
  //       console.log(err);
  //     }
  //   })
  // }

