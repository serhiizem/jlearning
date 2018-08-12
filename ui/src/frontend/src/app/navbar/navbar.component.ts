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
  }

  logout() {
    this.http.post('logout', {})
      .subscribe(() => {
        this.authService.setAuthenticated(false);
      });
  }
}
