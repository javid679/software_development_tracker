import { Component, OnInit, Inject, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { TaskService } from 'src/app/shared/services/task-service';
import { Router } from '@angular/router';
  

@Component({
    selector: 'create-task',
    templateUrl: './create-task.component.html',
    styleUrls: ['./create-task.component.scss']
})

//component for create tasks in a project
export class CreateTaskComponent implements OnInit{
  name = new FormControl('');
  description = new FormControl('');
  assignedTo = new FormControl('');
  reportTo =new FormControl('');
  status = new FormControl('');
  issueType = new FormControl('');
  selectedValue: any | undefined;
  selectedProjectId: Number| undefined;
  loggedInUser: Number | undefined;
  projectMembers: any[] | undefined;
  statusList: any[] | undefined;
  issueTypeList: any[] | undefined;
    toggleCreateTask: boolean = false;
    errorMessage: { error: any; } | undefined;
    
    constructor(private taskService : TaskService,private router: Router) {}
  

    handleToggleCreateTask() {
        this.toggleCreateTask = !this.toggleCreateTask;
    }

    //creating a task with its respective attributes
    createTask(name: any, description: any, assignedTo: any, reportTo: any, status: any, issueType: any, ) {
        let task = {
            Name: name,
            Description: description,
            CreatedBy: this.loggedInUser,
            AssignedTo: assignedTo,
            ReportTo: reportTo,
            StatusId: status,
            IssueTypeId: issueType,
            ProjectId: this.selectedProjectId
        }
        console.log(task);
        this.taskService.createNewTask(task).subscribe({
            next: (response: any) => {
                console.log(response)
            },
            error: (err: any) => this.errorMessage = err
        })
        this.getAllTaskforUser(this.selectedProjectId);
        this.router.navigate(['./project-dashboard']);
        this.toggleCreateTask = !this.toggleCreateTask;
    }

    //get all tasks assigned to the user.
    getAllTaskforUser(projectId: any) {
        this.taskService.getUsersforTask(projectId).subscribe({
            next: (Response: any) => {
              this.projectMembers = Response;
        },
            error: (err: { error: any; }) => {
                this.errorMessage = err;
                console.log(err.error)
            }
            
        });
    }

    ngOnInit() {
        this.selectedProjectId = Number(localStorage.getItem('selectedProject'));
        this.loggedInUser = Number(localStorage.getItem('userId'))
        this.getAllTaskforUser(this.selectedProjectId);

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