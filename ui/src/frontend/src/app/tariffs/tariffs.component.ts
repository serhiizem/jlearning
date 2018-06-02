import {Component} from '@angular/core';
import 'rxjs/add/operator/finally';
import {TariffsService} from "../shared/tariffs.service";
import {HttpClient} from "@angular/common/http";

@Component({
  templateUrl: 'tariffs.component.html',
  styleUrls: ['tariffs.component.css']
})
export class TariffsComponent {
  regions: any[];

  constructor(private http: HttpClient, private tariffsService: TariffsService) {
  }

  ngOnInit() {
    this.tariffsService.getAllRegions().subscribe(res => {
      this.regions = res;
      console.log(res)
    });
  }
}
