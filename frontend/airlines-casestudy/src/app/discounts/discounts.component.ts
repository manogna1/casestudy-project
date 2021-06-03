import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
//import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-discounts',
  templateUrl: './discounts.component.html',
  styleUrls: ['./discounts.component.css']
})
export class DiscountsComponent implements OnInit {
 // @ViewChild('discountModal') discountModal: ElementRef;
  
  submitted : boolean =false;
  isAdded : boolean =false;
  isUpdated : boolean =false;
  isDeleted : boolean =false;
  addDiscountForm : FormGroup;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.addDiscountForm = this.formBuilder.group({
      name: ['', Validators.required],
      code: ['', Validators.required],
      percent: ['', Validators.required],
      from: ['', Validators.required],
      to: ['', [Validators.required]],
      status: ['', [Validators.required]],
    });
  }
  get f() { return this.addDiscountForm.controls; }

  handleSaveDiscount() {
    this.submitted = true;
    if (this.addDiscountForm.invalid) {
      console.log('Invalid Form!!');
      return;
    }
    document.getElementById("discountModal").click();
    console.log(this.addDiscountForm.value);
    this.isAdded=true;
    // this.discountService.sendSaveRequest(this.bugForm.value).subscribe(res => {
    //   console.log(res);
    // });
  }
}
