import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(private auth: AuthService) {}

  email = new FormControl('don@don.don', [
    Validators.required,
    Validators.email,
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
    email: this.email,
    password: this.password,
  });

  async login() {
    this.showAlert = true;
    this.alertMsg = 'Please wait! Your are logging in.';
    this.alertColor = 'blue';
    this.inSubmision = true;

    this.auth.login(this.loginForm.value as User).subscribe({
      next: (data) => {
        console.log(data);
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
