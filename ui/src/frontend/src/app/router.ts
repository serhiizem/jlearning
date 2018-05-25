import {Routes} from "@angular/router";
import {MainPageComponent} from "./main/main-page.component";

export const appRoutes: Routes = [
  {path: '', redirectTo: 'main-page', pathMatch: 'full'},
  {path: 'main-page', component: MainPageComponent},
];
