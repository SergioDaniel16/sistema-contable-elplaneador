import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CatalogoCuentas, CatalogoCuentasDTO } from '../models/catalogo-cuentas.model';

@Injectable({
  providedIn: 'root'
})
export class CatalogoCuentasService {
  private apiUrl = 'http://18.119.103.37/api/catalogo';

  constructor(private http: HttpClient) { }

  obtenerActivas(): Observable<CatalogoCuentas[]> {
    return this.http.get<CatalogoCuentas[]>(`${this.apiUrl}/activas`);
  }

  obtenerTodas(): Observable<CatalogoCuentas[]> {
    return this.http.get<CatalogoCuentas[]>(this.apiUrl);
  }

  crear(cuenta: CatalogoCuentasDTO): Observable<CatalogoCuentas> {
    return this.http.post<CatalogoCuentas>(this.apiUrl, cuenta);
  }

  actualizar(id: number, cuenta: CatalogoCuentasDTO): Observable<CatalogoCuentas> {
    return this.http.put<CatalogoCuentas>(`${this.apiUrl}/${id}`, cuenta);
  }

  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
