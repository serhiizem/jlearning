import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {CookieService} from "ngx-cookie-service";
import {LoaderService} from "./loader.service";
import {Subject} from "rxjs/Subject";

@Injectable()
export class HttpClientService {

  constructor(private cookieService: CookieService,
              private http: HttpClient,
              private loaderService: LoaderService) {
  }

  // get<T>(url: string, resultState:  <T> ) {
  //   let httpHeaders = new HttpHeaders()
  //     .set('Authorization', 'Bearer ' + this.cookieService.get("access_token"));
  //
  //   this.loaderService.show();
  //   this.http.get<T>(url, {
  //     headers: httpHeaders
  //   }).map(res => {
  //     resultState.next(res);
  //     this.loaderService.hide();
  //   });
  // }

  post(url: string, body: Object): void {
    let httpHeaders = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.cookieService.get("access_token"));
    this.http.post(url, body, {headers: httpHeaders}).subscribe();
  }
}
