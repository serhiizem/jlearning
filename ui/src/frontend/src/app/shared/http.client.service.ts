import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {CookieService} from "ngx-cookie-service";
import {Observable} from "rxjs/Observable";
import {ACCESS_TOKEN_VALUE} from "./constants";

@Injectable()
export class HttpClientService {

  constructor(private cookieService: CookieService,
              private http: HttpClient) {
  }

  get<T>(url: string, params?: any): Observable<T> {
    let authHeaders = this.getAuthHeaders();
    let httpParams = this.extractHttpParams(params);

    return this.http.get<T>(url, {
      headers: authHeaders,
      params: httpParams
    });
  };

  post(url: string, body: Object): Observable<Object> {
    let authHeaders = this.getAuthHeaders();
    return this.http.post(url, body, {headers: authHeaders});
  }

  delete(url: string): Observable<Object> {
    let authHeaders = this.getAuthHeaders();
    return this.http.delete(url, {headers: authHeaders});
  }

  getAuthHeaders(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.cookieService.get(ACCESS_TOKEN_VALUE));
  }

  extractHttpParams(params?: any): HttpParams {
    let httpParams = new HttpParams();
    if (params) {
      for (let attr in params) {
        if (Object.prototype.hasOwnProperty.call(params, attr)) {
          let val = params[attr];
          httpParams.set(attr, val);
        }
      }
    }
    return httpParams;
  }
}
