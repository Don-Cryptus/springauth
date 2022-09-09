import { Component, Input, OnInit } from '@angular/core';
import { UnsplashResponse } from 'src/app/model/unsplash.model';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css'],
})
export class GalleryComponent implements OnInit {
  @Input() images?: UnsplashResponse;

  constructor() {}

  ngOnInit(): void {}
}
