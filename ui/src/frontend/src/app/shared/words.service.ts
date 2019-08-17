import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {WordGroup} from "./types";
import {ALL_USER_WORDS, USER_VERBS} from "./endpoints";
import {HttpClientService} from "./http.client.service";
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";
import {TranslatableWord} from "../words/words.component";

@Injectable()
export class WordsService {

  private wordGroupsSubject = new Subject<WordGroup[]>();

  wordGroupsState = this.wordGroupsSubject.asObservable();

  constructor(private httpClientService: HttpClientService) {
  }

  loadUserVerbs(): Observable<void> {
    return this.httpClientService.get<WordGroup[]>(USER_VERBS).map(res => {
      this.wordGroupsSubject.next(res);
    });
  }

  addVerb(verb: TranslatableWord): Observable<Object> {
    return this.httpClientService.post(USER_VERBS, verb);
  }

  allUserWords(): Observable<TranslatableWord[]> {
    return this.httpClientService.get<TranslatableWord[]>(ALL_USER_WORDS);
  }
}
