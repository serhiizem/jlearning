import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";
import {CookieService} from "ngx-cookie-service";

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
}
