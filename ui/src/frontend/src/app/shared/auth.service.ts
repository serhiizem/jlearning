import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import * as decode from 'jwt-decode';
import {LoaderService} from "./loader.service";
import {ACCESS_TOKEN} from "./endpoints";
import {ACCESS_TOKEN_VALUE} from "./constants";

export class User {
  id: string = '';
  username: string = '';

  constructor(id: string, username: string) {
    this.id = id;
    this.username = username;
  }
}

@Injectable()
export class AuthService {

  private _authenticated = new Subject<boolean>();
  private userSubject = new Subject<User>();

  _authenticated$ = this._authenticated.asObservable();
  userState = this.userSubject.asObservable();

  constructor(private http: HttpClient,
              private cookieService: CookieService,
              private router: Router,
              private loaderService: LoaderService) {
  }

  doLogin(username: string, password: string) {
    let params = new URLSearchParams();
    params.set('username', username);
    params.set('password', password);
    params.set('grant_type', 'password');
    let headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Basic ' + btoa("webapp:webapp")
    });

    this.loaderService.show();
    this.http.post(ACCESS_TOKEN, params.toString(), {
      headers: headers
    })
      .subscribe(token => {
          this.saveToken(token);
          this.setAuthenticated(true);
          let user: User = this.getUserFromToken(token);
          this.userSubject.next(user);
          this.loaderService.hide();
          this.doRedirectBasedOnToken(token);
        },
        err => {
          console.log(err)
        });
  }

  private saveToken(token) {
    let expireDate = new Date().getTime() + (1000 * token.expires_in);
    this.cookieService.set(ACCESS_TOKEN_VALUE, token.access_token, expireDate);
  }

  private doRedirectBasedOnToken(token) {
    let decodedToken = decode(token.access_token);
    let authorities = decodedToken.authorities;
    if (this.userAdminOrStudent(authorities)) {
      this.router.navigate(['/words'])
    }
  }

  private userAdminOrStudent(authorities): boolean {
    return authorities.indexOf("ROLE_ADMIN") > -1 || authorities.indexOf("ROLE_STUDENT") > -1;
  }

  setAuthenticated(authenticated: boolean) {
    this._authenticated.next(authenticated);
  }

  getUserFromToken(token): User {
    return new User(token.user_id, token.username);
  }

  handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
}
