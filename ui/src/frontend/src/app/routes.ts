import {Routes} from "@angular/router";
import {DemoComponent} from "./demo/demo.component";
import {TariffsComponent} from "./tariffs/tariffs.component";
import {TroubleshootingComponent} from "./troubleshooting/troubleshooting.component";

export const routes: Routes = [
  {path: 'dashboard', component: DemoComponent},
  {path: 'tariffs', component: TariffsComponent},
  {path: 'troubleshooting', component: TroubleshootingComponent},
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
];
