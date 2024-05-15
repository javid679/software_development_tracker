import { Component, OnInit, Inject } from '@angular/core';
import {Dialog, DialogRef, DIALOG_DATA} from '@angular/cdk/dialog';
import { ProjectService } from '../shared/services/project-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';

export interface PeriodicElement {
    name: string;
    description: string;
  }
  
  

@Component({
    selector: 'user-dashboard',
    templateUrl: './user-dashboard.component.html',
    styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit{
  displayedColumns: string[] = ['projectName', 'description', 'createdBy', 'createdOn'];
  res: any;
  dataSource: any;
  project_name: string | undefined;
  project_description: string | undefined;
  errorMessage: any;
  showCreateProject: boolean = false;
  userId: Number | undefined;
  name = new FormControl('');
  description = new FormControl('');
  constructor(public dialog: Dialog, private projectService : ProjectService,private router: Router, private _snackBar: MatSnackBar) {
    this._snackBar.open('Logged In Successfully..', '', {
      duration: 3000
  });
  }

  toggleCreateproject() {
    this.showCreateProject = !this.showCreateProject;
  }

  createProject(projectName: any, projectDescription: any) {
    const project = {
      Name: projectName,
      Description: projectDescription,
      userID: this.userId
   }
   this.projectService.createNewProject(project).subscribe({
    next: (response: any) => {
        console.log(response);
        this.toggleCreateproject();
    },
    error: (err: { error: any; }) => {
        this.errorMessage = err;
        console.log(err.error)
    }
    
});
  }

  openProject(selectedProject: any) {
    localStorage.setItem('selectedProject',selectedProject.id)
    this.router.navigate(['./project-dashboard']);
  }


 
  ngOnInit() {
    localStorage.removeItem('selectedProject');
     this.userId = Number(localStorage.getItem("userId"));
    if(this.userId) {
      this.projectService.getUserProjects(this.userId).subscribe({
        next: (Response: any) => {
            this.dataSource = Response;
            console.log(this.dataSource);
    },
        error: (err: { error: any; }) => {
            this.errorMessage = err;
            console.log(err.error)
        }
        
    });
    }
  }
  
}

