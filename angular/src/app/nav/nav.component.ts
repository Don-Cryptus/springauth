import { Component, HostListener, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})
export class NavComponent implements OnInit {
  userMenu = false;
  navbar = false;
  wasInside = false;
  constructor() {}

  ngOnInit(): void {}

  @HostListener('click')
  clickInside() {
    this.wasInside = true;
  }

  @HostListener('document:click')
  clickout() {
    if (!this.wasInside) {
      this.userMenu = false;
      this.navbar = false;
    }
    this.wasInside = false;
  }
}
