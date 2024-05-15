import { Optional } from '@angular/core';
import { Component, OnInit, Inject, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TaskService } from 'src/app/shared/services/task-service';

@Component({
  selector: 'task-detailed-view-dashboard',
  templateUrl: './task-detailed-view.component.html',
  styleUrls: ['./task-detailed-view.component.scss'],
})
export class TaskDetailedViewComponent implements OnInit {
  selectedTaskId: Number | undefined;
  errorMessage: any | undefined;
  selectedTask: any | undefined;
  editMode: boolean = false;
  selectedProjectId: any | undefined;
  loggedInUser: any | undefined;
  projectMembers: any[] | undefined;
  statusList: any[] | undefined;
  issueTypeList: any[] | undefined;
  status = new FormControl('');
  Type = new FormControl('');
  reportTo = new FormControl('');
  assignedTo = new FormControl('');
  editDescription = new FormControl('');

  defaultStatus: string | undefined;

  constructor(
    public dialogRef: MatDialogRef<TaskDetailedViewComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    public taskService: TaskService
  ) {
    this.selectedTaskId = Number(data.id);
    console.log(this.selectedTaskId);
  }

  doneEditing(selectedTask: any,name: any, description: any,Type: any,reportTo: any,assignedTo: any,status: any) {
    selectedTask.name = name;
    selectedTask.description = description;
    selectedTask.issueStatus = status;
    selectedTask.issueType = Type;
    selectedTask.assignedTo = assignedTo;
    selectedTask.reportTo = reportTo;

    this.taskService.updateTask(selectedTask).subscribe({
      next: (Response: any) => {
        console.log(Response);
        
      },
      error: (err: any) => {
        err = this.errorMessage
      }
    })
  }

  ngOnInit() {
    this.taskService.getSelectedTaskDetails(this.selectedTaskId).subscribe({
      next: (Response: any) => {
        this.selectedTask = Response;
        this.defaultStatus = this.selectedTask.issueStatus;
        console.log("***",this.defaultStatus);
      },
      error: (err: { error: any }) => {
        this.errorMessage = err;
        console.log(err.error);
      },
    });

    this.selectedProjectId = Number(localStorage.getItem('selectedProject'));
        this.loggedInUser = Number(localStorage.getItem('userId'))
        this.taskService.getUsersforTask(this.selectedProjectId).subscribe({
            next: (Response: any) => {
              this.projectMembers = Response;
        },
            error: (err: { error: any; }) => {
                this.errorMessage = err;
                console.log(err.error)
            }
            
        });
        this.taskService.getStatusList().subscribe({
            next: (Response: any) => {
              this.statusList = Response;
        },
            error: (err: { error: any; }) => {
                this.errorMessage = err;
                console.log(err.error)
            }
            
        });

        this.taskService.getIssueType().subscribe({
            next: (Response: any) => {
              this.issueTypeList = Response;
        },
            error: (err: { error: any; }) => {
                this.errorMessage = err;
                console.log(err.error)
            }
            
        });
  }
}
