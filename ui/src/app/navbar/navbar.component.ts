import {Component, isDevMode, OnInit} from '@angular/core';
import {AuthService} from "../shared/auth.service";
import {HttpClientService} from "../shared/http.client.service";
import {REVOKE_TOKEN} from "../shared/endpoints";
import {ACCESS_TOKEN_VALUE, IS_AUTHENTICATED, USERNAME} from "../shared/constants";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";

@Component({
  selector: 'navbar',
  templateUrl: 'navbar.component.html',
  styleUrls: ['navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private authenticated: boolean = false;
  private username: string;

  constructor(private httpClientService: HttpClientService,
              private authService: AuthService,
              private cookieService: CookieService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.setAuthentication();
    this.setUsername();

    if (isDevMode()) {
      this.authenticated = true;
    } else {
      this.authService._authenticated$.subscribe(
        isAuthenticated => {
          this.authenticated = isAuthenticated;
        })
    }
    this.authService.userState.subscribe(userState => {
      this.username = userState.username;
      localStorage.setItem(USERNAME, userState.username);
    })
  }

  private setAuthentication(): void {
    let authenticated = localStorage.getItem(IS_AUTHENTICATED);
    if (authenticated !== undefined) {
      this.authenticated = Boolean(authenticated);
    }
  }

  private setUsername(): void {
    let username = localStorage.getItem(USERNAME);
    if (username !== undefined) {
      this.username = username;
    }
  }

  logout() {
    this.httpClientService.delete(REVOKE_TOKEN)
      .subscribe(() => {
        this.authService.setAuthenticated(false);
        this.cookieService.delete(ACCESS_TOKEN_VALUE);
        localStorage.removeItem(IS_AUTHENTICATED);
        localStorage.removeItem(USERNAME);

        this.router.navigate(['/login']);
      });
  }
}
