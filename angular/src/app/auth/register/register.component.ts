import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginReq } from 'src/app/model/login.model';
import { RegisterReq } from 'src/app/model/register.model';
import { AuthService } from 'src/app/services/auth.service';
import { RegisterValidators } from '../validators/register-validators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  constructor(private auth: AuthService) {}

  username = new FormControl('don', [
    Validators.required,
    Validators.minLength(3),
  ]);
  email = new FormControl('don@don.don', [
    Validators.required,
    Validators.email,
  ]);
  password = new FormControl('C4ohJ7A3zPux8q', [
    Validators.required,
    Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/),
  ]);
  confirm_password = new FormControl('C4ohJ7A3zPux8q', [Validators.required]);

  inSubmision = false;
  showAlert = false;
  alertMsg = 'Please wait! Your account is being created.';
  alertColor = 'blue';

  ngOnInit(): void {}

  registerForm = new FormGroup(
    {
      username: this.username,
      email: this.email,
      password: this.password,
      confirm_password: this.confirm_password,
    },
    [RegisterValidators.match('password', 'confirm_password')]
  );

  async register() {
    this.showAlert = true;
    this.alertMsg = 'Please wait! Your account is being created.';
    this.alertColor = 'blue';
    this.inSubmision = true;

    this.auth.register(this.registerForm.value as RegisterReq).subscribe({
      next: (data) => {
        console.log(data);
        this.alertMsg = 'Success! Your account has been created.';
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
