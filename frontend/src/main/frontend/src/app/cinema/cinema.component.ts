import { Component, OnInit } from '@angular/core';
import {Film, Kino, MetaServiceApiService, Reservierung} from "../generated/rest/comparison";
import {NgbModal, ModalDismissReasons} from "@ng-bootstrap/ng-bootstrap";
import SpieltageEnum = Film.SpieltageEnum;

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
  private menuSelected: boolean = false
  private reservation: Reservierung = {}

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
      this.menuSelected = false
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  openReservations(reservations, movie: Film) {
    this.movieSelected = movie
    this.modalService.open(reservations, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.daySelected = null
      this.menuSelected = false
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
    var reservierung: Reservierung = {
      name: this.personSelected.valueOf(),
      menu: this.menuSelected,
      tag: this.daySelected.substring(0,2)
    };

    this.cinemaService.createReservationUsingPOST(this.cinemaSelected.kino, this.movieSelected.titel, reservierung).subscribe(res =>
    this.loadCinema())
  }

  linesplitter(): number {
    return 12/this.cinemaList.length
  }

  loadCinema(): void {
    this.cinemaService.getAllMoviesUsingGET(this.cinemaSelected.kino).subscribe(movies =>{
    this.cinemaSelected.filme = movies
    const help = this.movieSelected
    this.cinemaSelected.filme.filter(f => {
      if(f.titel == help.titel)
        this.movieSelected = f;
    })
    }
  )
  }

  deleteReservation(reservation: Reservierung): void{

    this.cinemaService.removeReservationUsingPOST(this.cinemaSelected.kino, this.movieSelected.titel, reservation).subscribe( res =>
      this.loadCinema()
    )
  }

}
