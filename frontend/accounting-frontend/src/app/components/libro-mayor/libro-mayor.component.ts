import { Component, OnInit } from '@angular/core';
import { LibroMayorService } from '../../services/libro-mayor.service';
import { LibroMayor, BalanceComprobacion } from '../../models/libro-mayor.model';

@Component({
  selector: 'app-libro-mayor',
  templateUrl: './libro-mayor.component.html',
  styleUrls: ['./libro-mayor.component.css']
})
export class LibroMayorComponent implements OnInit {
  libroMayor: LibroMayor[] = [];
  balance?: BalanceComprobacion;

  constructor(private libroMayorService: LibroMayorService) {}

  ngOnInit(): void {
    this.cargarLibroMayor();
    this.cargarBalance();
  }

  cargarLibroMayor(): void {
    this.libroMayorService.obtenerLibroMayor().subscribe({
      next: (data) => {
        this.libroMayor = data;
      },
      error: (error) => {
        console.error('Error al cargar libro mayor:', error);
      }
    });
  }

  cargarBalance(): void {
    this.libroMayorService.obtenerBalance().subscribe({
      next: (data) => {
        this.balance = data;
      },
      error: (error) => {
        console.error('Error al cargar balance:', error);
      }
    });
  }

  calcularTotalDebe(): number {
    return this.libroMayor.reduce((total, item) => total + item.totalDebe, 0);
  }

  calcularTotalHaber(): number {
    return this.libroMayor.reduce((total, item) => total + item.totalHaber, 0);
  }

  esBalanceado(): boolean {
    return this.balance ? this.balance.diferencia < 0.01 : false;
  }
}
