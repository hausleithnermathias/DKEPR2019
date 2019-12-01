import { Component, OnInit } from '@angular/core';
import {Kino, MetaServiceApiService} from "../generated/rest/comparison";

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {

  private cinemaList: Kino[] = []

  constructor(private cinemaService: MetaServiceApiService) { }

  ngOnInit() {
    this.cinemaService.getAllCinemasUsingGET().subscribe(cinemas =>{
      this.cinemaList = cinemas

      console.log(this.cinemaList)
    })


  }

}
