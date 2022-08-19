import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { RegisterValidators } from '../validators/register-validators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  constructor(private auth: AuthService) {}

  name = new FormControl('don', [Validators.required, Validators.minLength(3)]);
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
      name: this.name,
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

    try {
      this.auth.register(this.registerForm.value as User);
    } catch (e) {
      console.error(e);
      this.alertMsg =
        'An unexpected error has occurred. Please try again later.';
      this.alertColor = 'red';
      this.inSubmision = false;
      return;
    }

    this.alertMsg = 'Success! Your account has been created.';
    this.alertColor = 'green';
  }
}
