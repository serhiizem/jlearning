import {Routes} from "@angular/router";
import {TariffsComponent} from "./tariffs/tariffs.component";
import {TroubleshootingComponent} from "./troubleshooting/troubleshooting.component";
import {HomeComponent} from "./home/home.component";

export const routes: Routes = [
  {path: 'dashboard', component: HomeComponent},
  {path: 'tariffs', component: TariffsComponent},
  {path: 'troubleshooting', component: TroubleshootingComponent},
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
];
