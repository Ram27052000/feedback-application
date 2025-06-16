import {Injectable} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

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
}
