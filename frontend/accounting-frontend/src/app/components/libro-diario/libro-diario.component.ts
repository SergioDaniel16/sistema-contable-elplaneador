import { Component, OnInit } from '@angular/core';
import { LibroDiarioService } from '../../services/libro-diario.service';
import { CatalogoCuentasService } from '../../services/catalogo-cuentas.service';
import { LibroDiario, LibroDiarioDTO, AsientoDTO } from '../../models/libro-diario.model';
import { CatalogoCuentas } from '../../models/catalogo-cuentas.model';

@Component({
  selector: 'app-libro-diario',
  templateUrl: './libro-diario.component.html',
  styleUrls: ['./libro-diario.component.css']
})
export class LibroDiarioComponent implements OnInit {
  cuentas: CatalogoCuentas[] = [];
  registros: LibroDiario[] = [];
  asiento: LibroDiarioDTO = {
    fecha: new Date().toISOString().split('T')[0],
    descripcion: '',
    asientos: []
  };
  mensaje: string = '';
  mensajeClase: string = '';

  constructor(
    private libroDiarioService: LibroDiarioService,
    private catalogoService: CatalogoCuentasService
  ) {}

  ngOnInit(): void {
    this.cargarCuentas();
    this.cargarRegistros();
    this.agregarAsiento();
    this.agregarAsiento();
  }

  cargarCuentas(): void {
    this.catalogoService.obtenerActivas().subscribe({
      next: (data) => {
        this.cuentas = data;
      },
      error: (error) => {
        console.error('Error al cargar cuentas:', error);
      }
    });
  }

  cargarRegistros(): void {
    this.libroDiarioService.obtenerTodos().subscribe({
      next: (data) => {
        this.registros = data;
      },
      error: (error) => {
        console.error('Error al cargar registros:', error);
      }
    });
  }

  agregarAsiento(): void {
    this.asiento.asientos.push({
      cuentaId: 0,
      debe: 0,
      haber: 0
    });
  }

  eliminarAsiento(index: number): void {
    if (this.asiento.asientos.length > 1) {
      this.asiento.asientos.splice(index, 1);
    }
  }

  calcularTotalDebe(): number {
    return this.asiento.asientos.reduce((total, a) => total + (a.debe || 0), 0);
  }

  calcularTotalHaber(): number {
    return this.asiento.asientos.reduce((total, a) => total + (a.haber || 0), 0);
  }

  calcularDiferencia(): number {
    return Math.abs(this.calcularTotalDebe() - this.calcularTotalHaber());
  }

  estBalanceado(): boolean {
    const diferencia = this.calcularDiferencia();
    const totalDebe = this.calcularTotalDebe();
    const totalHaber = this.calcularTotalHaber();
    
    return diferencia < 0.01 && totalDebe > 0 && totalHaber > 0;
  }

  registrarAsiento(): void {
    if (!this.estBalanceado()) {
      this.mostrarMensaje('Error: El debe y el haber deben ser iguales', 'error-message');
      return;
    }

    // Limpiar asientos con valores en 0
    const asientosFiltrados = this.asiento.asientos.filter(a => 
      (a.debe > 0 || a.haber > 0) && a.cuentaId > 0
    );

    if (asientosFiltrados.length < 2) {
      this.mostrarMensaje('Error: Debe tener al menos 2 cuentas con movimientos', 'error-message');
      return;
    }

    const asientoEnviar = {
      ...this.asiento,
      asientos: asientosFiltrados
    };

    this.libroDiarioService.registrarAsiento(asientoEnviar).subscribe({
      next: (response) => {
        this.mostrarMensaje('Asiento registrado exitosamente en servidor: ' + response.serverIp, 'success-message');
        this.cargarRegistros();
        this.limpiarFormulario();
      },
      error: (error) => {
        this.mostrarMensaje('Error al registrar asiento: ' + (error.error || 'Error desconocido'), 'error-message');
        console.error('Error:', error);
      }
    });
  }

  limpiarFormulario(): void {
    this.asiento = {
      fecha: new Date().toISOString().split('T')[0],
      descripcion: '',
      asientos: []
    };
    this.agregarAsiento();
    this.agregarAsiento();
  }

  calcularTotalDebeRegistro(registro: LibroDiario): number {
    return registro.asientos.reduce((total, a) => total + a.debe, 0);
  }

  calcularTotalHaberRegistro(registro: LibroDiario): number {
    return registro.asientos.reduce((total, a) => total + a.haber, 0);
  }

  mostrarMensaje(texto: string, clase: string): void {
    this.mensaje = texto;
    this.mensajeClase = clase;
    setTimeout(() => {
      this.mensaje = '';
    }, 5000);
  }
}
