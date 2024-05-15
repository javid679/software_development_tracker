import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FormControl, Validators} from '@angular/forms';
import { UserService } from '../shared/services/user-service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})

//component for logging in
export class LoginComponent implements OnInit{
    email = new FormControl('', [Validators.required, Validators.email]);
    password = new FormControl('', [Validators.required]);
    hide = true;
    loadComplete = false;
    validateFlag = 'Y';
    buttonShow = true;
    passwordShow = false;
    errorMessage: any;
    constructor(private router: Router, private userService: UserService) {}

    //method for logging in
    async login(login_id: string | null, password: string | null) {
        let userDetails: any = {
            Email: login_id,
            Password: password
        }
        
        await this.userService.userLogin(userDetails).subscribe({
            next: (Response: any) => {
                console.log(Response);
            let res = [];
            res.push(Response);
            localStorage.setItem("userId",res[0].body);
            if(localStorage.getItem('userId')) {
                this.router.navigate(['./user-dashboard'])
            }
        },
            error: (err: { error: any; }) => {
                this.errorMessage = err;
                console.log(err.error)
                this.router.navigate(['./evowner']);
            }
            
        });
        
    }

    //email cant be empty and should be valid
    getErrorMessage() {
        return this.email.hasError('required') ? 'You must enter a value' :
            this.email.hasError('email') ? 'Not a valid email' :
                '';
    }
    
    //password cant be empty
    getPasswordErrorMsg() {
        return this.password.hasError('required') ? 'Please enter your password' : '';
    }

    //method for getting user details
    async getUserDetails(emailId: any,password: any) {
        /* this.loginService.getUserDetails(emailId, password).subscribe({
            next: Response => {
                console.log("Response is", Response);
                let userdetail = []
                userdetail.push(Response)
                localStorage.setItem("userDetails", JSON.stringify(userdetail[0]));
            },
            error: err => {
                this.errorMessage = err;
                console.log(err.error.userstatus);
                this.router.navigate(['./evowner']);
            }
        }); */
    }
    async toggle(login_id: any) {
        /* let userDetails: any = {
            emailId: login_id,
            validateUser: this.validateFlag
        }
        this.loginService.validateUser(userDetails).subscribe({
            next: (Response: any) => {
            console.log("Response is", Response);
            let res = [];
            res.push(Response);
            console.log("Res is",res);
            if(res[0].userstatus == "VALID"){
                this.passwordShow = !this.passwordShow;
                this.buttonShow = !this.buttonShow;
                this.validateFlag = 'N'
            }
            else if(res[0].userstatus == "INVALID")
            {
                this.redirectToRegister;
            }
            
        },
            error: (err: { error: { userstatus: any; }; }) => {
                this.errorMessage = err;
                console.log(err.error.userstatus)
                
            }
            
        }); */
    }

    ngOnInit() {
        localStorage.clear()
        
        setTimeout(() => {
            this.loadComplete = true;
        }, 1500);
    }
    redirectToRegister() {
        this.router.navigate(['./register']);  
    }
    redirectToForgotPassword() {
        this.router.navigate(['./forgot-password']);
    }
}