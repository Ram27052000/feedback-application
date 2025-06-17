import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FeedbackListComponent} from "./feedback-list/feedback-list.component";
import {FeedbackFormComponent} from "./feedback-form/feedback-form.component";

const routes: Routes = [
  {path: 'feedback-form', component: FeedbackFormComponent},
  {path : 'feedback-list', component: FeedbackListComponent},
  {path: '', redirectTo:'feedback-form', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
