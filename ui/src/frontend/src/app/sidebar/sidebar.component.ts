import {Component, isDevMode, OnInit} from '@angular/core';
import {AuthService} from "../shared/auth.service";
import {HttpClient} from "@angular/common/http";

declare interface RouteInfo {
  path: string;
  title: string;
}

export const ROUTES: RouteInfo[] = [
  {path: 'words', title: 'word entry'},
  {path: 'test', title: 'test'}
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  private authenticated: boolean = false;
  menuItems: any[];

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.menuItems = ROUTES;

    if (isDevMode()) {
      this.authenticated = true;
    } else {
      this.authService._authenticated$.subscribe(
        isAuthenticated => {
          this.authenticated = isAuthenticated;
        })
    }
  }
}
