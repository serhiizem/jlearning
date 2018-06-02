import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";
import {GATEWAY_URI} from "./constants";

@Injectable()
export class TariffsService {

  regions: any[];

  constructor(private http: HttpClient) {
  }

  getAllRegions(): Observable<any[]> {
    return this.http.get(`${GATEWAY_URI}/regions`)
      .map(res => res as any[]);
  }

  handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
}
