import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user.model';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  public register({ email, password, username }: User): Observable<User> {
    return this.http.post<User>(`${environment.url}/api/auth/register`, {
      email,
      password,
      username,
    });
  }

  public login({ email, password }: User): Observable<User> {
    return this.http.post<User>(`${environment.url}/api/auth/login`, {
      email,
      password,
    });
  }
}
