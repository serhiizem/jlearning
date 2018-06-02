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

import {
  MatButtonModule,
  MatCardModule, MatDatepickerModule, MatDatepickerToggle,
  MatFormFieldModule,
  MatIconModule, MatInputModule,
  MatMenuModule, MatNativeDateModule, MatPaginatorModule,
  MatSelectModule, MatTableModule,
  MatTabsModule,
  MatToolbarModule
} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CdkTableModule} from "@angular/cdk/table";
import {TariffsService} from "./shared/tariffs.service";

@NgModule({
  imports: [
    MatInputModule,
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatTabsModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    CdkTableModule,
    MatPaginatorModule
  ],
  exports: [
    MatInputModule,
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatTabsModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    CdkTableModule,
    MatPaginatorModule
  ]
})
export class MaterialModule {}

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
    BrowserAnimationsModule,
    MaterialModule,
    RouterModule.forRoot(routes, { useHash: true }),
  ],
  providers: [
    AuthService,
    TariffsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
