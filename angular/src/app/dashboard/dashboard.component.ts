import { Component, OnInit } from '@angular/core';
import { UnsplashResponse } from '../model/unsplash.model';
import { DashboardService } from '../services/dashboard.service';
import { UnsplashService } from '../services/unsplash.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  query: string = '';
  images?: UnsplashResponse;
  constructor(
    public dashboard: DashboardService,
    public unsplash: UnsplashService
  ) {}

  ngOnInit(): void {
    this.dashboard.all().subscribe({
      next(value) {
        console.log(value);
      },
      error(err) {
        console.log(err);
      },
    });
  }

  getQuery($event: string) {
    this.query = $event;
    this.unsplash.searchUnsplash(this.query).subscribe({
      next: (value) => {
        this.images = value;
        console.log(this.images);
      },
      error: (err) => {
        console.log(err);
      },
    });

    console.log(this.query);
  }
}
