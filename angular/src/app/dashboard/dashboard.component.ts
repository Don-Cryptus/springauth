import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  constructor(public dashboard: DashboardService) {}

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
}
