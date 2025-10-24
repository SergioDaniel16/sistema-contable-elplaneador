export interface BalanceGeneral {
  totalActivos: number;
  totalPasivos: number;
  totalCapital: number;
  totalPasivoCapital: number;
  balanceado: boolean;
}

export interface EstadoResultados {
  totalIngresos: number;
  totalGastos: number;
  utilidad: number;
  tieneUtilidad: boolean;
}
