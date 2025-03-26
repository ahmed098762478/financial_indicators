import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from './login-request.model';
import { LoginResponse } from './login-response.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Example: point to your Spring Boot server.
  // Adjust to match your actual server URL and port.
  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  /**
   * Call POST /api/auth/login with email/password
   */
  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, request);
  }

  /**
   * Persist the token (simplest is localStorage).
   */
  storeToken(token: string): void {
    localStorage.setItem('authToken', token);
  }

  /**
   * Retrieve the current token
   */
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  /**
   * Check if user is "logged in"
   */
  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  /**
   * Log out user
   */
  logout(): void {
    localStorage.removeItem('authToken');
  }
}
