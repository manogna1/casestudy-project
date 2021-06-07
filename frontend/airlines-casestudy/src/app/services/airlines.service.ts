import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Airline } from '../airlines/model/Airline';

@Injectable({
  providedIn: 'root'
})
export class AirlinesService {
airlines : Airline[];
airline : Airline;
  constructor(private http: HttpClient) { }

  getAllAirlines() : Observable<Airline[]>{
    return this.http.get("http://localhost:3000/airlines")
    .pipe(map((res: any) => {
     this.airlines=res;
      return this.airlines;
    }));
}

addAirline( airline : Airline):any{
  return this.http.post("http://localhost:3000/airlines", airline,{
    headers:{
        "content-type":"application/json"
  }}).subscribe(res =>{
    return res;});
}

editAirline( airline : Airline, id:number):any{
  return this.http.put("http://localhost:3000/airlines/"+id, airline,{
    headers:{
        "content-type":"application/json"
  }}).subscribe(res =>{
    return res;});
}

getAirline(id : number) : Observable<Airline>{
  return this.http.get("http://localhost:3000/airlines/"+id)
  .pipe(map((res: any) => {
   this.airline=res;
    return this.airline;
  }));
}
deleteAirline(id : number) : Observable<Airline>{
  return this.http.delete("http://localhost:3000/airlines/"+id)
  .pipe(map((res: any) => {
   this.airline=res;
    return this.airline;
  }));
}
}
