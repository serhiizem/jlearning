import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {CookieService} from "ngx-cookie-service";
import {Observable} from "rxjs/Observable";

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

  getAuthHeaders(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.cookieService.get("access_token"));
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
