import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterReq, RegisterRes } from '../model/register.model';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { LoginReq, LoginRes } from '../model/login.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

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
}
