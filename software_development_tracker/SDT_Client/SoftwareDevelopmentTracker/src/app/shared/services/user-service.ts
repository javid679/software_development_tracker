import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})

//This service to handle API calls for handliing user related data.
export class UserService {
    data: any=[]
    parentUrl ="https://localhost:44338/Account/"
    
    constructor(private http: HttpClient) {}

    //method for registering new user.
    userSignUp(user: any): Observable<any[] | HttpResponse<any[]>> {
        let url = this.parentUrl+"Register";
        
        return this.http
      .post(url, user, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
        observe: 'response',
        responseType: 'text',
      })
      .pipe(
        tap((response: any) => {
          return response;
        }),
        catchError(this.handleError)
      );
    }

    // method for logging in user
    userLogin(user: any): Observable<any[] | HttpResponse<any[]>> {
        let url = this.parentUrl+"Login";
        return this.http.post<any[]>(url, user, { observe: 'response'}).pipe(
            tap((response: any)=> {return response})
        );

    }
    
    //method for updating user password
    updateUserPassword(user: any) {
        let url =this.parentUrl+"users/update/"+user.email;
        let httpHeaders = new HttpHeaders({
            'Content-Type' : 'application/json',
            'Authorization' : 'Basic ' +  btoa('admin:password')
       });
        return this.http.put<any[]>(url,user,{headers: httpHeaders,responseType: 'json', observe: 'response'})
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