import { Injectable } from '@angular/core';
import { Discount } from '../discounts/model/Discount';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class DiscountService {
  discounts:Discount[];
  constructor(private http: HttpClient) { }

  getAllDiscounts() : Observable<Discount[]>{
    return this.http.get("http://localhost:3000/discounts")
    .pipe(map((res: any) => {
     this.discounts=res;
      return this.discounts;
    }));
    
  }
}
