import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {HttpClientModule} from '@angular/common/http';
import {TariffsComponent} from "./tariffs/tariffs.component";
import {TroubleshootingComponent} from "./troubleshooting/troubleshooting.component";
import {RouterModule} from "@angular/router";
import {AppComponent} from "./app.component";
import {routes} from "./routes";
import {AuthService} from "./shared/auth.service";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {HomeComponent} from "./home/home.component";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TariffsComponent,
    TroubleshootingComponent,
    SidebarComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes, { useHash: true }),
  ],
  providers: [
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
