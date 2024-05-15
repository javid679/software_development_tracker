

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppMaterialModule } from './app.material.module';
import { AppComponent, DialogContentComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatExpansionModule } from '@angular/material/expansion';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { CommonModule } from '@angular/common';  
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatMenuModule} from '@angular/material/menu';
import {MatDialogModule} from '@angular/material/dialog';
import { CreateAccountComponent } from './create-account/create-account.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ProjectDashboardComponent } from './project-dashboard/project-dashboard.component';
import { CreateTaskComponent } from './project-dashboard/create-task/create-task.component';
import { TaskDetailedViewComponent } from './project-dashboard/task-detailed-view/task-detailed-view.component';


const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: CreateAccountComponent },
  { path: 'user-dashboard', component: UserDashboardComponent},
  { path: 'project-dashboard', component: ProjectDashboardComponent},
  { path: 'forgot-password', component: ForgotPasswordComponent},
  { path: '**', redirectTo: 'login', pathMatch: 'full' },
];

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppMaterialModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatNativeDateModule,
    MatRippleModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatButtonToggleModule,
    MatMenuModule,
    MatDialogModule,
    DragDropModule,
    CommonModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  declarations: [
    AppComponent,
    DialogContentComponent,
    LoginComponent,
    CreateAccountComponent,
    UserDashboardComponent,
    ProjectDashboardComponent,
    ForgotPasswordComponent,
    CreateTaskComponent,
    TaskDetailedViewComponent,
  ],
  entryComponents: [DialogContentComponent],
  bootstrap: [AppComponent],
  providers: []
})
export class AppModule { }
