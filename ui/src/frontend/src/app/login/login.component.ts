import {Subscription} from "rxjs/Subscription";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Component, OnDestroy} from "@angular/core";
import {CookieService} from "ngx-cookie-service";

@Component({
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.css']
})
export class LoginComponent implements OnDestroy {

  loginData = {username: "", password: ""};
  authenticated = false;
  subscription: Subscription;

  constructor(private cookieService: CookieService, private http: HttpClient) {

  }

  login() {
    let params = new URLSearchParams();
    params.set('username', this.loginData.username);
    params.set('password', this.loginData.password);
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
        data => {
          console.log(data)
        },
        err => {
          console.log(err)
        });
  }

  saveToken(token) {
    let expireDate = new Date().getTime() + (1000 * token.expires_in);
    this.cookieService.set("access_token", token.access_token, expireDate);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
