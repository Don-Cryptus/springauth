import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InputComponent } from './input/input.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AlertComponent } from './alert/alert.component';
import { SearchComponent } from './search/search.component';

@NgModule({
  declarations: [InputComponent, AlertComponent, SearchComponent],
  imports: [CommonModule, ReactiveFormsModule],
  exports: [InputComponent, AlertComponent, SearchComponent],
})
export class SharedModule {}
