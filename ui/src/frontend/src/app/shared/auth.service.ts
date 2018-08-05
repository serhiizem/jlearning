import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import * as decode from 'jwt-decode';

@Injectable()
export class AuthService {

  private _authenticated = new Subject<boolean>();

  _authenticated$ = this._authenticated.asObservable();

  constructor(private http: HttpClient,
              private cookieService: CookieService,
              private router: Router) {
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

    this.http.post('/uaa/oauth/token', params.toString(), {
      headers: headers
    })
      .subscribe(token => {
          this.saveToken(token);
          this.setAuthenticated(true);
          this.doRedirectBasedOnToken(token);
        },
        err => {
          console.log(err)
        });
  }

  private saveToken(token) {
    let expireDate = new Date().getTime() + (1000 * token.expires_in);
    this.cookieService.set("access_token", token.access_token, expireDate);
  }

  private doRedirectBasedOnToken(token) {
    let decodedToken = decode(token.access_token);
    let authorities = decodedToken.authorities;
    if (authorities.indexOf("ROLE_CSR") > -1) {
      this.router.navigate(['/tariffs'])
    }
  }

  setAuthenticated(authenticated: boolean) {
    this._authenticated.next(authenticated);
  }

  handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
}
