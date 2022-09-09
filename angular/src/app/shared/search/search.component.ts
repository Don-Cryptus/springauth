import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  @Input() value: String = '';

  constructor() {}

  ngOnInit(): void {}

  onChange($event: Event) {
    this.value = ($event?.target as HTMLInputElement).value;
  }
}
