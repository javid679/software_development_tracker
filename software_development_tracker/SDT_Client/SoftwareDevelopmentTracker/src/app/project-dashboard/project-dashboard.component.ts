import { Component, OnInit, Inject, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TaskService } from '../shared/services/task-service';
import {
    CdkDragDrop,
    moveItemInArray,
    transferArrayItem
  } from '@angular/cdk/drag-drop';
import { MatDialog } from '@angular/material/dialog';
import { TaskDetailedViewComponent } from './task-detailed-view/task-detailed-view.component';
  

@Component({
    selector: 'project-dashboard',
    templateUrl: './project-dashboard.component.html',
    styleUrls: ['./project-dashboard.component.scss']
})

//component for displaying project dashboard.
export class ProjectDashboardComponent implements OnInit {
    selectedProjectId: Number | undefined;
    userId: Number | undefined;
    errorMessage: { error: any; } | undefined;
    backlog: any = [];
    inProgress: any= [];
    done: any = [];

    constructor(private taskService : TaskService,private router: Router, public dialog: MatDialog) {}
    
    openDialog(ongoing: any) {
        console.log(ongoing);
        const dialogRef = this.dialog.open(TaskDetailedViewComponent, {
            data:ongoing
          });
      }
  
    /* drop(event: CdkDragDrop<any[]>) {
      if (event.previousContainer === event.container) {
        moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
      } else {
        transferArrayItem(
            
          event.previousContainer.data,
          event.container.data,
          event.previousIndex,
          event.currentIndex,
         
        );
        console.log(event.container.element.nativeElement)
        console.log("inprog",this.inProgress)
         this.taskService.updateTaskStatus(event.container.data[event.currentIndex], event.container).subscribe({
            next: (response: any) => {
                console.log(response);
                let res = [];
                res.push(Response);
                
            },
            error: (err:any) => this.errorMessage = err
        })
      }
    } */

    ngOnInit() {
        this.selectedProjectId = Number(localStorage.getItem('selectedProject'));
        this.userId = Number(localStorage.getItem('userId'));
        if(this.userId && this.selectedProjectId) {
            this.taskService.getUserProjectTasks(this.selectedProjectId,this.userId).subscribe({
              next: (Response: any) => {
                for(let i=0; i<Response.length ; i++) {
                    if(Response[i].issueStatus == 'Open') {
                        this.backlog.push(Response[i]);
                    }
                    else if(Response[i].issueStatus == 'In-Progress') {
                        this.inProgress.push(Response[i]);
                    }
                    else if(Response[i].issueStatus == 'Done') {
                        this.done.push(Response[i]);
                    }
                }
          },
              error: (err: { error: any; }) => {
                  this.errorMessage = err;
                  console.log(err.error)
              }
              
          });
          }
    }
}