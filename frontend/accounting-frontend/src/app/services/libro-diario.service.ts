import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LibroDiario, LibroDiarioDTO } from '../models/libro-diario.model';

@Injectable({
  providedIn: 'root'
})
export class LibroDiarioService {
  private apiUrl = 'http://18.119.103.37/api/libro-diario';

  constructor(private http: HttpClient) { }

  registrarAsiento(asiento: LibroDiarioDTO): Observable<LibroDiario> {
    return this.http.post<LibroDiario>(this.apiUrl, asiento);
  }

  obtenerTodos(): Observable<LibroDiario[]> {
    return this.http.get<LibroDiario[]>(this.apiUrl);
  }

  obtenerPorId(id: number): Observable<LibroDiario> {
    return this.http.get<LibroDiario>(`${this.apiUrl}/${id}`);
  }
}
