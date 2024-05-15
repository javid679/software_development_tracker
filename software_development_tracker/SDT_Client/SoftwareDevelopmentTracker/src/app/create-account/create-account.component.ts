import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl, Validators } from '@angular/forms';
import { UserService } from '../shared/services/user-service';

@Component({
  selector: 'create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss'],
})

//The CreateAccountComponent is used for registration of new user
export class CreateAccountComponent implements OnInit {
    full_name = new FormControl('', [Validators.required, Validators.minLength(4)]);
    mail = new FormControl('', [Validators.required, Validators.email]);
    new_password = new FormControl('', [Validators.required, Validators.minLength(8)]);
    confirm_password = new FormControl('', [Validators.required, Validators.minLength(8)]);
    hide = true;
    loadComplete = false;
    isDisabled = false;
    errorMessage: any;
    registerSuccess: boolean = false;
    constructor(private router: Router, private _snackBar: MatSnackBar, private userService: UserService) {}

    //Method for validating full name
    getNameErrorMessage() {
        return this.full_name.hasError('required') ? 'You must enter a value' :
            this.full_name.hasError('minlength') ? 'Should be atleast 4 characters long' :
                '';
    }

    //Method for validating email
    getMailErrorMessage() {
        return this.mail.hasError('required') ? 'You must enter a value' :
            this.mail.hasError('email') ? 'Not a valid email' :
                '';
    }

    //Method for validating password
    getPasswordErrorMessage() {
        return this.new_password.hasError('required') ? 'You must enter a value' :
            this.new_password.hasError('minlength') ? 'Should be atleast 8 characters long' : '';
    }

    getConfirmPasswordErrorMessage() {
        return this.confirm_password.hasError('required') ? 'You must enter a value' :
            this.confirm_password.hasError('minlength') ? 'Should be atleast 8 characters long' : this.passwordMismatch() ? 'Password does not match' : '';
    }

    // method for matching passwords
    passwordMismatch() {
        if (this.new_password !== this.confirm_password) {
            return true;
        } else {
            return false;
        }
    }
   
    //method for registering users
    async registeredUser(fullname: any,mail: any,newPassword: any) {
        const user = {
            Name: fullname,
            Email: mail,
            Password: newPassword,
         }
         this.userService.userSignUp(user).subscribe({
             next: (response: any) => {
                 console.log(response);
                    this.registerSuccess = true; 
             },
             error: (err: any) => this.errorMessage = err
         })
         this.router.navigate(['./login']);
    }

    ngOnInit() {
        setTimeout(() => {
            this.loadComplete = true;
        }, 500);
    }
}