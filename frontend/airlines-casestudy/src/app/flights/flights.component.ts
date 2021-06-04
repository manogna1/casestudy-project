import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css']
})
export class FlightsComponent implements OnInit {
  isAdded : boolean = false;
  isUpdated : boolean = false;
  isDeleted : boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
