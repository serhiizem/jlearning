import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {TelecomProviderAppComponent} from './telecom-provider-app.component';
import {HttpClientModule} from '@angular/common/http';
import {TariffComponent} from "./tariffs/tariff.component";
import {MainPageComponent} from "./main/main-page.component";
import {RouterModule} from "@angular/router";
import {appRoutes} from "./router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule, MatCheckboxModule, MatFormFieldModule, MatInputModule} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    TelecomProviderAppComponent,
    MainPageComponent,
    TariffComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [TelecomProviderAppComponent]
})
export class AppModule {
}
