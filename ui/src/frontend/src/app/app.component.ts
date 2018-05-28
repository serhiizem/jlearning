import {Component, OnDestroy} from '@angular/core';
import 'rxjs/add/operator/finally';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./shared/auth.service";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css']
})
export class AppComponent implements OnDestroy {

  authenticated = false;
  subscription: Subscription;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.subscription = this.authService._authenticated$.subscribe(authenticated => {
      this.authenticated = authenticated;
    })
  }

  logout() {
    this.http.post('logout', {}).finally(() => {
      this.authService.setAuthenticated(false);
    }).subscribe();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
