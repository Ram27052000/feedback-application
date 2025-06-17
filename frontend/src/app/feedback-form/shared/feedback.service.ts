import {Injectable} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {FeedbackForm} from "../models/feedbackform";

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private baseUrl = '/api'
  constructor(private http: HttpClient) {
  }

  submitFeedbackForm(formData: FormGroup): Observable<void>{
      console.log("submitFeedbackForm", formData);
      return this.http.post<void>(`${this.baseUrl}/feedback`, formData)
  }

  getFeedbacks(): Observable<FeedbackForm[]>{
      return this.http.get<FeedbackForm[]>(`${this.baseUrl}/get-feedback`)
  }
}
