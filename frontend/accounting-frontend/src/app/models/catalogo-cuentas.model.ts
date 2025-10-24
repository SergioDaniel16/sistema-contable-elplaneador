export interface CatalogoCuentas {
  id?: number;
  codigo: string;
  nombre: string;
  tipo: string;
  naturaleza: string;
  activa?: boolean;
  createdAt?: string;
  serverIp?: string;
}

export interface CatalogoCuentasDTO {
  codigo: string;
  nombre: string;
  tipo: string;
  naturaleza: string;
}
