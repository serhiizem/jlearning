import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {HttpClient} from "@angular/common/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";
import {UserRole} from "./types/user.role";

@Injectable()
export class AuthService {

  private _authenticated = new Subject<boolean>();

  constructor(private http: HttpClient) {
  }

  _authenticated$ = this._authenticated.asObservable();

  setAuthenticated(authenticated: boolean) {
    this._authenticated.next(authenticated);
  }

  getCurrentUserRoles() {
    this.http
      .get<UserRole[]>("user")
      .map(function (response) {
        console.log(response);
        return "";
      }).catch(this.handleError);
  }

  handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
}
