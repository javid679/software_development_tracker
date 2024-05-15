import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
  HttpResponse,
} from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';


//this service will handle the api calls for handling project related data.
@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  data: any = [];
  parentUrl = 'https://localhost:44338/Project/';

  constructor(private http: HttpClient) {}

  //get projects associated with a particular user.
  getUserProjects(userId: any) {
    let url = this.parentUrl + `GetProjects?UserID=${userId}`;
    return this.http.get<any[]>(url).pipe(
      tap((data: any) => console.log('All:', JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  //create a new project
  createNewProject(project: any): Observable<any[] | HttpResponse<any[]>> {
    let url = this.parentUrl + 'SaveProject';

    return this.http
      .post<any[]>(url, project, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
        observe: 'response',
      })
      .pipe(
        tap((response: any) => {
          return response;
        }),
        catchError(this.handleError)
      );
  }
  private handleError(err: HttpErrorResponse) {
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      errorMessage = `An error occured: ${err.error.message}`;
    } else {
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }
}
