import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginReq } from 'src/app/model/login.model';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(
    private auth: AuthService,
    private tokenStorage: TokenStorageService
  ) {}

  username = new FormControl('don', [
    Validators.required,
    Validators.minLength(3),
  ]);
  password = new FormControl('C4ohJ7A3zPux8q', [
    Validators.required,
    Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/),
  ]);

  inSubmision = false;
  showAlert = false;
  alertMsg = 'Please wait!';
  alertColor = 'blue';

  ngOnInit(): void {}

  loginForm = new FormGroup({
    username: this.username,
    password: this.password,
  });

  async login() {
    this.showAlert = true;
    this.alertMsg = 'Please wait! Your are logging in.';
    this.alertColor = 'blue';

    this.auth.login(this.loginForm.value as LoginReq).subscribe({
      next: (data) => {
        console.log(data);
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.alertMsg = 'Success! Your have been logged in.';
        this.alertColor = 'green';
        this.inSubmision = false;
      },
      error: (err) => {
        console.error(err);
        this.alertMsg = err.error.message;
        this.alertColor = 'red';
        this.inSubmision = false;
      },
    });
  }
}
