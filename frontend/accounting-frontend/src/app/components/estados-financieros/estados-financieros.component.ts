import { Component, OnInit } from '@angular/core';
import { EstadosFinancierosService } from '../../services/estados-financieros.service';
import { BalanceGeneral, EstadoResultados } from '../../models/estados-financieros.model';

@Component({
  selector: 'app-estados-financieros',
  templateUrl: './estados-financieros.component.html',
  styleUrls: ['./estados-financieros.component.css']
})
export class EstadosFinancierosComponent implements OnInit {
  balanceGeneral?: BalanceGeneral;
  estadoResultados?: EstadoResultados;

  constructor(private estadosFinancierosService: EstadosFinancierosService) {}

  ngOnInit(): void {
    this.cargarBalanceGeneral();
    this.cargarEstadoResultados();
  }

  cargarBalanceGeneral(): void {
    this.estadosFinancierosService.obtenerBalanceGeneral().subscribe({
      next: (data) => {
        this.balanceGeneral = data;
      },
      error: (error) => {
        console.error('Error al cargar balance general:', error);
      }
    });
  }

  cargarEstadoResultados(): void {
    this.estadosFinancierosService.obtenerEstadoResultados().subscribe({
      next: (data) => {
        this.estadoResultados = data;
      },
      error: (error) => {
        console.error('Error al cargar estado de resultados:', error);
      }
    });
  }
}
