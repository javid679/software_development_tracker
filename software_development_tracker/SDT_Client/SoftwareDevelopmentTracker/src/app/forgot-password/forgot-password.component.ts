import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FormControl, Validators} from '@angular/forms';
import { UserService } from '../shared/services/user-service';

@Component({
    selector: 'forgot-password',
    templateUrl: './forgot-password.component.html',
    styleUrls: ['./forgot-password.component.scss']
})

//Class component for resetting password
export class ForgotPasswordComponent {
    email = new FormControl('', [Validators.required, Validators.email]);
    new_password = new FormControl('', [Validators.required]);
    confirm_password = new FormControl('', [Validators.required]);
    hide = true;
    loadComplete = false;
    validateFlag = 'Y';
    buttonShow = true;
    passwordShow = false;
    errorMessage: any;

    constructor(private router: Router, private userService: UserService) {}

    //email cat be empty and should be valid
    getErrorMessage() {
        return this.email.hasError('required') ? 'You must enter a value' :
            this.email.hasError('email') ? 'Not a valid email' :
                '';
    }
    
    //password cant be empty
    getPasswordErrorMsg() {
        return this.new_password.hasError('required') ? 'Please enter your password' : '';
    }

    //function for updating password
    async updatePassword(email: string | null, updatedPassword: string | null) {
        let userDetails: any = {
            email: email,
            password: updatedPassword
        } 
        this.userService.updateUserPassword(userDetails).subscribe({
            next: response => {
                console.log(response);
                let res = [];
                res.push(Response);
                
            },
            error: err => this.errorMessage = err
        })
    }
}
