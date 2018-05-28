import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {DemoComponent} from './demo/demo.component';
import {HttpClientModule} from '@angular/common/http';
import {TariffsComponent} from "./tariffs/tariffs.component";
import {TroubleshootingComponent} from "./troubleshooting/troubleshooting.component";
import {RouterModule} from "@angular/router";
import {AppComponent} from "./app.component";
import {routes} from "./routes";
import {AuthService} from "./shared/auth.service";

@NgModule({
  declarations: [
    AppComponent,
    DemoComponent,
    TariffsComponent,
    TroubleshootingComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
  ],
  providers: [
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
