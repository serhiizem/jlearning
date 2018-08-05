import {Component, OnInit} from '@angular/core';
import {AuthService} from "../shared/auth.service";
import {HttpClient} from "@angular/common/http";

declare interface RouteInfo {
  path: string;
  title: string;
  icon?: string;
  class?: string;
}

export const ROUTES: RouteInfo[] = [
  {path: 'dashboard', title: 'Dashboard'},
  {path: 'troubleshooting', title: 'Troubleshooting'},
  {path: 'tariffs', title: 'Tariffs'}
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  menuItems: any[];

  constructor(private authService: AuthService, private http: HttpClient) {
  }

  ngOnInit() {
    this.menuItems = ROUTES;
  }
}
