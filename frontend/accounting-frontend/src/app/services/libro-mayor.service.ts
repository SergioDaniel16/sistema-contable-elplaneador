import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LibroMayor, BalanceComprobacion } from '../models/libro-mayor.model';

@Injectable({
  providedIn: 'root'
})
export class LibroMayorService {
  private apiUrl = 'http://18.119.103.37/api/libro-mayor';

  constructor(private http: HttpClient) { }

  obtenerLibroMayor(): Observable<LibroMayor[]> {
    return this.http.get<LibroMayor[]>(this.apiUrl);
  }

  obtenerBalance(): Observable<BalanceComprobacion> {
    return this.http.get<BalanceComprobacion>(`${this.apiUrl}/balance`);
  }
}
