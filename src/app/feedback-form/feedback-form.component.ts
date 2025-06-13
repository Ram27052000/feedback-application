import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {FeedbackForm} from "./models/feedbackform";

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
  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.submitted = true;
    if(form.invalid){
        return;
    }
    form.reset();
    this.submitted = false;
    console.log('form', form)
  }
}
