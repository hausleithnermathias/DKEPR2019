import { Component, OnInit } from '@angular/core';
import {Film, Kino, MetaServiceApiService} from "../generated/rest/comparison";
import {NgbModal, ModalDismissReasons} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {

  closeResult: string;
  private cinemaList: Kino[]
  private isCinemaSelected: boolean = false
  private cinemaSelected: Kino = {}
  private movieSelected: Film = {}
  private daySelected: Film.SpieltageEnum
  private personSelected: String

  constructor(private cinemaService: MetaServiceApiService, private modalService: NgbModal) { }

  ngOnInit() {
    this.cinemaService.getAllCinemasUsingGET().subscribe(cinemas =>{

      this.cinemaList = cinemas
    })
  }

  open(content, movie: Film) {
    this.movieSelected = movie
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.daySelected = null
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  showMovies(i: number){
    this.cinemaSelected = this.cinemaList[i]
    this.isCinemaSelected = true
  }

  onSubmit(){
    console.log(this.daySelected)
    console.log(this.personSelected)
  }

}
