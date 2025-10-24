export interface AsientoDTO {
  cuentaId: number;
  debe: number;
  haber: number;
}

export interface LibroDiarioDTO {
  fecha: string;
  descripcion: string;
  asientos: AsientoDTO[];
}

export interface AsientoContable {
  id: number;
  cuenta: any;
  debe: number;
  haber: number;
}

export interface LibroDiario {
  id: number;
  fecha: string;
  descripcion: string;
  asientos: AsientoContable[];
  createdAt: string;
  serverIp: string;
}
