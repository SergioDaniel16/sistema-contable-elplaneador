import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LoginRequest } from '../../models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials: LoginRequest = {
    username: '',
    password: ''
  };

  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onLogin(): void {
    this.errorMessage = '';
    this.successMessage = '';

    this.authService.login(this.credentials).subscribe({
      next: (response) => {
        this.successMessage = `¡Bienvenido ${response.nombre}!`;
        this.authService.saveToken(response.token);
        
        // Redirigir al dashboard después de 1 segundo
        setTimeout(() => {
          this.router.navigate(['/dashboard']);
        }, 1000);
      },
      error: (error) => {
        this.errorMessage = 'Usuario o contraseña incorrectos';
        console.error('Error en login:', error);
      }
    });
  }
}