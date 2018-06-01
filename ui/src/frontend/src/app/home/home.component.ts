import {Component} from '@angular/core';
import 'rxjs';
import {HttpClient} from "@angular/common/http";

@Component({
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.css']
})
export class HomeComponent {

  constructor(private http: HttpClient) {
  }
}
