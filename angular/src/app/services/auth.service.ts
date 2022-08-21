import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRes, User } from '../model/register.model';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { LoginRes } from '../model/login.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  public register({
    email,
    password,
    username,
  }: User): Observable<RegisterRes> {
    return this.http.post<User>(`${environment.url}/api/auth/register`, {
      email,
      password,
      username,
    });
  }

  public login({ email, password }: User): Observable<LoginRes> {
    return this.http.post<User>(`${environment.url}/api/auth/login`, {
      email,
      password,
    });
  }
}
