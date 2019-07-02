import {Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {WordsComponent} from "./words/words.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'words', component: WordsComponent},
  {path: '', redirectTo: 'login', pathMatch: 'full'},
];
