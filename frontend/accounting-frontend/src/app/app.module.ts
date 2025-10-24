import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CatalogoCuentasComponent } from './components/catalogo-cuentas/catalogo-cuentas.component';
import { LibroDiarioComponent } from './components/libro-diario/libro-diario.component';
import { LibroMayorComponent } from './components/libro-mayor/libro-mayor.component';
import { EstadosFinancierosComponent } from './components/estados-financieros/estados-financieros.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    CatalogoCuentasComponent,
    LibroDiarioComponent,
    LibroMayorComponent,
    EstadosFinancierosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }