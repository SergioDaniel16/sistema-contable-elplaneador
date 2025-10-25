import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  serverInfo: string = 'Cargando...';

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    // Hacer petición para obtener el servidor actual
    this.authService.login({username: 'admin', password: '1234admin1234'}).subscribe({
      next: (response) => {
        this.serverInfo = 'Servidor: ' + response.serverIp;
      },
      error: () => {
        this.serverInfo = 'No disponible';
      }
    });
  }
}