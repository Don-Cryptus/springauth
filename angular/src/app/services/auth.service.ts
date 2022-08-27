import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterReq, RegisterRes } from '../model/register.model';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { LoginReq, LoginRes } from '../model/login.model';
import { TokenStorageService } from './token-storage.service';
import {
  RefreshTokenRequest,
  RefreshTokenResponse,
} from '../model/refreshToken.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private tokenStorage: TokenStorageService
  ) {}

  public register({
    email,
    password,
    username,
  }: RegisterReq): Observable<RegisterRes> {
    return this.http.post<RegisterRes>(`${environment.url}/api/auth/register`, {
      email,
      password,
      username,
    });
  }

  public login({ username, password }: LoginReq): Observable<LoginRes> {
    return this.http.post<LoginRes>(`${environment.url}/api/auth/login`, {
      username,
      password,
    });
  }

  public refreshToken({
    refreshToken,
  }: RefreshTokenRequest): Observable<RefreshTokenResponse> {
    return this.http.post<RefreshTokenResponse>(
      `${environment.url}/api/auth/refreshtoken`,
      { refreshToken }
    );
  }

  public parseJwt(token: string) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      window
        .atob(base64)
        .split('')
        .map((c) => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
        .join('')
    );

    return JSON.parse(jsonPayload) as {
      exp: number;
      iat: number;
      sub: string;
    };
  }

  public isAuthenticated(): boolean {
    const token = this.tokenStorage.getToken();

    if (!token) return false;

    const jwt = this.parseJwt(token);
    console.log(jwt);
    // jwt token expires in the future

    return new Date(jwt.exp * 1000) > new Date();
  }
}
