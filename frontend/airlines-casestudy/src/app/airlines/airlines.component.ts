import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AirlinesService } from '../services/airlines.service';
import { Airline } from './model/Airline';

@Component({
  selector: 'app-airlines',
  templateUrl: './airlines.component.html',
  styleUrls: ['./airlines.component.css']
})
export class AirlinesComponent implements OnInit {
 airlinesList : Airline[];
 submitted : boolean =false;
 isAdded : boolean =false;
 isUpdated : boolean =false;
 isDeleted : boolean =false;
 addAirlineForm : FormGroup;
 duplicateAirline: Airline;
  constructor(private formBuilder: FormBuilder,private airlineService: AirlinesService) { }

  ngOnInit(): void {
    this.addAirlineForm = this.formBuilder.group({
      name: ['', Validators.required],
      operations: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contact: ['', Validators.required],
      address: ['', [Validators.required]],
    });
    this.airlineService.getAllAirlines().subscribe(res => {
      this.airlinesList = res;
      console.log(res);
    })
  }

  get f() { return this.addAirlineForm.controls; }

  handleSaveAirline() {
    this.submitted = true;
    if (this.addAirlineForm.invalid) {
      console.log('Invalid Form!!');
      return;
    }
    
    console.log(this.addAirlineForm.value);
    this.isAdded=true;
    document.getElementById("airlineModal").click();
    this.airlineService.addAirline(this.addAirlineForm.value).subscribe(res => {
        console.log(res);
      });
      
  }

  handleUpdateAirline(){
    document.getElementById("editAirlineModal").click();
    console.log(this.duplicateAirline);
    this.isUpdated=true;
    let id=this.duplicateAirline.id;
    this.airlineService.editAirline(this.duplicateAirline,id);
    // .subscribe(res => {
    //   console.log(res);
    // });
  }

  handleEditAirline(airline){
    this.duplicateAirline = { ...airline};
  }

  handleDeleteAirline(airline : any){
    confirm("Are you sure want to delete airline?");
    this.airlineService.deleteAirline(airline.id)
    .subscribe(res => {
      console.log(res);
    });
  }
}
