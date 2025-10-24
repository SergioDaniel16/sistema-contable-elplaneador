import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CatalogoCuentasComponent } from './components/catalogo-cuentas/catalogo-cuentas.component';
import { LibroDiarioComponent } from './components/libro-diario/libro-diario.component';
import { LibroMayorComponent } from './components/libro-mayor/libro-mayor.component';
import { EstadosFinancierosComponent } from './components/estados-financieros/estados-financieros.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'catalogo-cuentas', component: CatalogoCuentasComponent },
  { path: 'libro-diario', component: LibroDiarioComponent },
  { path: 'libro-mayor', component: LibroMayorComponent },
  { path: 'estados-financieros', component: EstadosFinancierosComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }