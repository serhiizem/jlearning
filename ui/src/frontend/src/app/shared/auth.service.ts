import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";

@Injectable()
export class AuthService {

  private _authenticated = new Subject<boolean>();

  _authenticated$ = this._authenticated.asObservable();

  setAuthenticated(authenticated: boolean) {
    this._authenticated.next(authenticated);
  }
}
