import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {AlertService} from "../../shared/alert.service";

@Component({
  selector: 'alert',
  templateUrl: 'alert.component.html',
  styleUrls: ['alert.component.css']
})
export class AlertComponent implements OnInit, OnDestroy {

  private subscription: Subscription;

  type: string;
  message: string;
  show: boolean = false;

  constructor(private alertService: AlertService) {
  }

  ngOnInit() {
    this.subscription = this.alertService.getState()
      .filter(state => state)
      .subscribe(state => {
        this.type = state.type;
        this.message = state.message;
        this.show = state.show;
      });
  }

  hide() {
    this.alertService.hide();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
