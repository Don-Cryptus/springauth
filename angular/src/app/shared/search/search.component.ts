import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  @Input() value: string = '';
  @Output() newValue = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  onChange($event: Event) {
    this.value = ($event?.target as HTMLInputElement).value;
    this.newValue.emit(this.value);
  }
}
