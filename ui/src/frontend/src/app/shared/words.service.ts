import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";
import {CookieService} from "ngx-cookie-service";
import {Tariff} from "./types/tariff";
import {map} from "rxjs/operators";

@Injectable()
export class TariffsService {

  constructor(private cookieService: CookieService,
              private http: HttpClient) {
  }

  getAllRegions(): Observable<any[]> {
    let httpHeaders = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.cookieService.get("access_token"));

    return this.http.get('/tariffs/regions', {
      headers: httpHeaders
    }).map(res => res as any[]);
  }

  getAllTariffs(): Observable<any[]> {
    let httpHeaders = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.cookieService.get("access_token"));

    return this.http.get('/tariffs/available-tariffs', {
      headers: httpHeaders
    }).map(res => res as any[]);
  }

  getTariffsWithPagination(filter = '', sortOrder = 'asc',
                           pageNumber = 0, pageSize = 3): Observable<Tariff[]> {
    return this.http.get('/tariffs/available-tariffs', {
      params: new HttpParams()
        .set('Authorization', 'Bearer ' + this.cookieService.get("access_token"))
        .set('filter', filter)
        .set('sortOrder', sortOrder)
        .set('pageNumber', pageNumber.toString())
        .set('pageSize', pageSize.toString())
    }).pipe(
      map(res => res["payload"])
    );
  }
}
