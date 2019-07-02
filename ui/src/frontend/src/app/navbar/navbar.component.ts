import {Component, isDevMode, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../shared/auth.service";

@Component({
  selector: 'navbar',
  templateUrl: 'navbar.component.html',
  styleUrls: ['navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private authenticated = false;
  private username: string;

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  ngOnInit(): void {
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
    })
  }

  logout() {
    this.http.post('logout', {})
      .subscribe(() => {
        this.authService.setAuthenticated(false);
      });
  }
}
