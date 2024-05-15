import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})

//this service will handle the api calls for handling task related data.
export class TaskService {
    data: any=[]
    parentUrl ="https://localhost:44338/Project/"
    
    constructor(private http: HttpClient) {}

    //get all the tasks associated with a particular project.
    getUserProjectTasks(selectedProjectId:any,userId: any) {
        let url =this.parentUrl+`GetTaskCards?ProjectId=${selectedProjectId}&UserId=${userId}`;
        return this.http.get<any[]>(url)
        .pipe (
            tap((data: any) => console.log('All:', JSON.stringify(data))),
            catchError(this.handleError)
        )
    }

    //update the status of a task
    updateTaskStatus(task: any, taskArray:any) {
        let url =this.parentUrl+"UpdateTaskStatus";
        let StatusId;
        if(task.issueStatus=="Open") {
            StatusId = 5
        } else if(task.issueStatus=="In-Progress") {
            StatusId = 6
        } else if(task.issueStatus=="Done") {
            StatusId = 7
        }
        return this.http.put<any[]>(url,task,{responseType: 'json', observe: 'response'})
        .pipe(
        tap((data: any) => console.log('All: ', JSON.stringify(data))),
        catchError(this.handleError))
    }

    //list the users associated with the project to get assigned a task.
    getUsersforTask(selectedProjectId: any) {
        let url =this.parentUrl+`GetMembersListForProject?ProjectId=${selectedProjectId}`;
        return this.http.get<any[]>(url)
        .pipe (
            tap((data: any) => console.log('All:', JSON.stringify(data))),
            catchError(this.handleError)
        )
    }

    //get the status of the task
    getStatusList() {
        let url =this.parentUrl+`GetStatusList`;
        return this.http.get<any[]>(url)
        .pipe (
            tap((data: any) => console.log('All:', JSON.stringify(data))),
            catchError(this.handleError)
        )
    }

    //get type of issue given to this task.
    getIssueType() {
        let url =this.parentUrl+`GetIssueTypeList`;
        return this.http.get<any[]>(url)
        .pipe (
            tap((data: any) => console.log('All:', JSON.stringify(data))),
            catchError(this.handleError)
        )
    }

    //create a new tak in this project
    createNewTask(task: any): Observable<any[] | HttpResponse<any[]>> {
        let url = this.parentUrl+"SaveTask";
        
        return this.http.post<any[]>(url, task, { observe: 'response'}).pipe(
            tap((response: any)=> {return response})
        );
    }

    getSelectedTaskDetails(id: any):Observable<any[] | HttpResponse<any[]>> {
        let url = this.parentUrl+`GetTaskData?TaskId=${id}`;
        
        return this.http.get<any[]>(url)
        .pipe (
            tap((data: any) => console.log('All:', JSON.stringify(data))),
            catchError(this.handleError)
        )
    } 

    updateTask(updatedTask: any) {
        let url =this.parentUrl+"SaveTask";
        return this.http.put<any[]>(url,updatedTask,{responseType: 'json', observe: 'response'})
        .pipe(
        tap((data: any) => console.log('All: ', JSON.stringify(data))),
        catchError(this.handleError)
      );
    }

    private handleError(err: HttpErrorResponse) {
        let errorMessage = '';
        if (err.error instanceof ErrorEvent){
            errorMessage = `An error occured: ${err.error.message}`;
        } else {
            errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
        }
        console.error(errorMessage);
        return throwError(errorMessage);
    }
}