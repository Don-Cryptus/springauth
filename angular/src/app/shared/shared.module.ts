import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InputComponent } from './input/input.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AlertComponent } from './alert/alert.component';
import { SearchComponent } from './search/search.component';
import { GalleryComponent } from './gallery/gallery.component';

@NgModule({
  declarations: [
    InputComponent,
    AlertComponent,
    SearchComponent,
    GalleryComponent,
  ],
  imports: [CommonModule, ReactiveFormsModule],
  exports: [InputComponent, AlertComponent, SearchComponent, GalleryComponent],
})
export class SharedModule {}
