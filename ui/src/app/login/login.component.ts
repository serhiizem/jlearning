import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/auth.service";

@Component({
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.css']
})
export class LoginComponent implements OnInit {
  active = 'login';

  loginForm: FormGroup;
  loginFormUsername: FormControl;
  loginFormPassword: FormControl;

  registerForm: FormGroup;
  registerFormUsername: FormControl;
  registerFormPassword: FormControl;
  emailAddress: FormControl;

  constructor(private authService: AuthService) {
  }

  login(formValues) {
    this.authService.doLogin(formValues.username, formValues.password)
  }

  ngOnInit(): void {
    this.loginFormUsername = new FormControl('', Validators.required);
    this.loginFormPassword = new FormControl('', Validators.required);

    this.loginForm = new FormGroup({
      username: this.loginFormUsername,
      password: this.loginFormPassword
    });

    this.registerFormUsername = new FormControl('', Validators.required);
    this.registerFormPassword = new FormControl('', Validators.required);
    this.emailAddress = new FormControl('', Validators.required);

    this.registerForm = new FormGroup({
      username: this.registerFormUsername,
      password: this.registerFormPassword,
      emailAddress: this.emailAddress
    })
  }

  isFieldValid(field: any) {
    return field.valid || field.pristine;
  }
}
