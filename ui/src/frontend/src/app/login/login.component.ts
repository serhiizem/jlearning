import {Subscription} from "rxjs/Subscription";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Component, OnDestroy, OnInit} from "@angular/core";
import {CookieService} from "ngx-cookie-service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
  active = 'login';

  loginForm: FormGroup;
  loginFormUsername: FormControl;
  loginFormPassword: FormControl;

  registerForm: FormGroup;
  registerFormUsername: FormControl;
  registerFormPassword: FormControl;
  emailAddress: FormControl;

  subscription: Subscription;

  constructor(private cookieService: CookieService, private http: HttpClient) {

  }

  login(formValues) {
    let params = new URLSearchParams();
    params.set('username', formValues.username);
    params.set('password', formValues.password);
    params.set('grant_type', 'password');
    params.set('client_id', 'fooClientIdPassword');
    let headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Basic ' + btoa("fooClientIdPassword:secret")
    });

    this.http.post('/uaa/oauth/token', params.toString(), {
      headers: headers
    })
      .subscribe(
        token => {
          console.log(token);
          this.saveToken(token);
          this.getRegions(token);
        },
        err => {
          console.log(err)
        });
  }

  saveToken(token) {
    let expireDate = new Date().getTime() + (1000 * token.expires_in);
    this.cookieService.set("access_token", token.access_token, expireDate);
  }

  getRegions(token) {
    let httpHeaders = new HttpHeaders()
      .set('Authentication', 'Bearer ' + token.access_token);

    this.http.get('/regions', {
      headers: httpHeaders
    }).subscribe(response => {
      console.log(response);
    })
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
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
