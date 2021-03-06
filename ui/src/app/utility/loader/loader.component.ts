import {Component, OnInit} from '@angular/core';
import 'rxjs/add/operator/finally';
import {LoaderService} from "../../shared/loader.service";
import {Subscription} from "rxjs/Subscription";
import {LoaderState} from "../../shared/types";

@Component({
  selector: 'angular-loader',
  templateUrl: 'loader.component.html',
  styleUrls: ['loader.component.css']
})
export class LoaderComponent implements OnInit {

  show = false;

  private subscription: Subscription;

  constructor(private loaderService: LoaderService) {
  }

  ngOnInit() {
    this.subscription = this.loaderService.loaderState
      .subscribe((state: LoaderState) => {
        this.show = state.show;
      });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
