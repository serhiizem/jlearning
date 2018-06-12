import {Component, OnDestroy} from '@angular/core';
import 'rxjs/add/operator/finally';
import {HttpClient} from "@angular/common/http";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css']
})
export class AppComponent implements OnDestroy {

  authenticated = false;
  subscription: Subscription;

  constructor(private http: HttpClient) {
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
