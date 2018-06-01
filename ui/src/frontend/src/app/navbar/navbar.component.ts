import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'navbar',
  templateUrl: 'navbar.component.html'
})
export class NavbarComponent {

  private authenticated = false;

  constructor(private http: HttpClient) {
  }

  logout() {
    this.http.post('logout', {})
      .subscribe(response => this.authenticated = false);
  }
}
