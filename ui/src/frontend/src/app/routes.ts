import {Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {TariffsComponent} from "./tariffs/tariffs.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'tariffs', component: TariffsComponent},
  {path: '', redirectTo: 'login', pathMatch: 'full'},
];
