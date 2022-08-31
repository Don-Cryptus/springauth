import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuardService {
  constructor(
    public auth: AuthService,
    public tokenStorage: TokenStorageService,
    public router: Router
  ) {}

  canActivate(): boolean {
    const jwtString = this.tokenStorage.getToken();
    const refreshToken = this.tokenStorage.getRefreshToken();
    if (!this.auth.isAuthenticated() || !jwtString || !refreshToken) {
      this.router.navigate(['login']);
      return false;
    }

    const jwt = this.auth.parseJwt(jwtString);

    if (
      jwt.exp * 1000 <
      new Date().setTime(new Date().getTime() - 1 * 60 * 60 * 1000)
    ) {
      this.auth.refreshToken(refreshToken);
    }

    return true;
  }
}
