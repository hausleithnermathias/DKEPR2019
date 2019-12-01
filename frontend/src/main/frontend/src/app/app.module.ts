import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './layout/header/header.component';
import {ContentComponent} from './layout/content/content.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CalendarModule, SliderModule} from "primeng/primeng";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {Ng5SliderModule} from 'ng5-slider';
import {HttpClientModule} from "@angular/common/http";
import {AutoCompleteModule} from 'primeng/autocomplete';
import {NgxCurrencyModule} from "ngx-currency";
import { CinemaComponent } from './cinema/cinema.component';
import {CardModule} from 'primeng/card';
import {MetaServiceApiService} from "./generated/rest/comparison";

export const customCurrencyMaskConfig = {
  align: "left",
  allowNegative: false,
  allowZero: true,
  decimal: ",",
  precision: 0,
  prefix: "",
  suffix: "",
  thousands: ".",
  nullable: true
};

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ContentComponent,
    CinemaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CalendarModule,
    BrowserAnimationsModule,
    Ng5SliderModule,
    HttpClientModule,
    AutoCompleteModule,
    CardModule,
    SliderModule,
    ReactiveFormsModule,
    NgxCurrencyModule.forRoot(customCurrencyMaskConfig)
  ],
  providers: [
    MetaServiceApiService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}

