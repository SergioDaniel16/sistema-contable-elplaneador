import { Component, OnInit } from '@angular/core';
import { CatalogoCuentasService } from '../../services/catalogo-cuentas.service';
import { CatalogoCuentas, CatalogoCuentasDTO } from '../../models/catalogo-cuentas.model';

@Component({
  selector: 'app-catalogo-cuentas',
  templateUrl: './catalogo-cuentas.component.html',
  styleUrls: ['./catalogo-cuentas.component.css']
})
export class CatalogoCuentasComponent implements OnInit {
  cuentas: CatalogoCuentas[] = [];
  cuenta: CatalogoCuentasDTO = {
    codigo: '',
    nombre: '',
    tipo: '',
    naturaleza: ''
  };
  modoEdicion: boolean = false;
  cuentaIdEdicion?: number;
  mensaje: string = '';
  mensajeClase: string = '';

  constructor(private catalogoService: CatalogoCuentasService) {}

  ngOnInit(): void {
    this.cargarCuentas();
  }

  cargarCuentas(): void {
    this.catalogoService.obtenerTodas().subscribe({
      next: (data) => {
        this.cuentas = data;
      },
      error: (error) => {
        console.error('Error al cargar cuentas:', error);
        this.mostrarMensaje('Error al cargar cuentas', 'error-message');
      }
    });
  }

  guardarCuenta(): void {
    if (this.modoEdicion && this.cuentaIdEdicion) {
      this.catalogoService.actualizar(this.cuentaIdEdicion, this.cuenta).subscribe({
        next: () => {
          this.mostrarMensaje('Cuenta actualizada exitosamente', 'success-message');
          this.cargarCuentas();
          this.cancelar();
        },
        error: (error) => {
          this.mostrarMensaje('Error al actualizar cuenta', 'error-message');
          console.error('Error:', error);
        }
      });
    } else {
      this.catalogoService.crear(this.cuenta).subscribe({
        next: () => {
          this.mostrarMensaje('Cuenta creada exitosamente', 'success-message');
          this.cargarCuentas();
          this.limpiarFormulario();
        },
        error: (error) => {
          this.mostrarMensaje('Error al crear cuenta', 'error-message');
          console.error('Error:', error);
        }
      });
    }
  }

  editar(cuenta: CatalogoCuentas): void {
    this.modoEdicion = true;
    this.cuentaIdEdicion = cuenta.id;
    this.cuenta = {
      codigo: cuenta.codigo,
      nombre: cuenta.nombre,
      tipo: cuenta.tipo,
      naturaleza: cuenta.naturaleza
    };
  }

  eliminar(id: number): void {
    if (confirm('¿Está seguro de eliminar esta cuenta?')) {
      this.catalogoService.eliminar(id).subscribe({
        next: () => {
          this.mostrarMensaje('Cuenta eliminada exitosamente', 'success-message');
          this.cargarCuentas();
        },
        error: (error) => {
          this.mostrarMensaje('Error al eliminar cuenta', 'error-message');
          console.error('Error:', error);
        }
      });
    }
  }

  cancelar(): void {
    this.modoEdicion = false;
    this.cuentaIdEdicion = undefined;
    this.limpiarFormulario();
  }

  limpiarFormulario(): void {
    this.cuenta = {
      codigo: '',
      nombre: '',
      tipo: '',
      naturaleza: ''
    };
  }

  mostrarMensaje(texto: string, clase: string): void {
    this.mensaje = texto;
    this.mensajeClase = clase;
    setTimeout(() => {
      this.mensaje = '';
    }, 3000);
  }
}
