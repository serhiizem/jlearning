import {Component, OnDestroy} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/finally';
import {AuthService} from "../shared/auth.service";
import {Subscription} from "rxjs/Subscription";

@Component({
  templateUrl: 'demo.component.html',
  styleUrls: ['demo.component.css']
})
export class DemoComponent implements OnDestroy {

  authenticated = false;
  subscription: Subscription;
  greeting = {};

  constructor(private http: HttpClient, private authService: AuthService) {
    this.authenticate();
  }

  authenticate() {

    this.http.get('user').subscribe(response => {
      if (response['name']) {
        this.authService.setAuthenticated(true);
        this.http.get('resource').subscribe(data => this.greeting = data);
      } else {
        this.authenticated = false;
      }
    }, () => {
      this.authenticated = false;
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
