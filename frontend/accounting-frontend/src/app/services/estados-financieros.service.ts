import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BalanceGeneral, EstadoResultados } from '../models/estados-financieros.model';

@Injectable({
  providedIn: 'root'
})
export class EstadosFinancierosService {
  private apiUrl = 'http://18.220.164.228:8080/api/estados-financieros';

  constructor(private http: HttpClient) { }

  obtenerBalanceGeneral(): Observable<BalanceGeneral> {
    return this.http.get<BalanceGeneral>(`${this.apiUrl}/balance-general`);
  }

  obtenerEstadoResultados(): Observable<EstadoResultados> {
    return this.http.get<EstadoResultados>(`${this.apiUrl}/estado-resultados`);
  }
}
