import {Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {WordsComponent} from "./words/words.component";
import {TestComponent} from "./test/test.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'words', component: WordsComponent},
  {path: 'test', component: TestComponent},
  {path: '', redirectTo: 'login', pathMatch: 'full'},
];
