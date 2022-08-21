import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AuthModule } from './auth/auth.module';
import { authInterceptorProviders } from './interceptor/auth.interceptor';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, AuthModule],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent],
})
export class AppModule {}
