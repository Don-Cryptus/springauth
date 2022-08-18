import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  public async register({ email, password }: User) {
    if (!password) {
      throw new Error('Password not provided!');
    }
    const user = this.http.get<User>(environment.url);
  }
}
