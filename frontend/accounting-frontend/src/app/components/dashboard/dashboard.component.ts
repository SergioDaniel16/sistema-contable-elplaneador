import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  serverInfo: string = 'Cargando...';

  ngOnInit(): void {
    // Aquí podrías obtener info del servidor si lo necesitas
    this.serverInfo = 'Nodo 1 - 18.220.164.228';
  }
}
