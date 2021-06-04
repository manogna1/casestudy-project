import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DiscountService } from '../services/discount.service';
import { Discount } from './model/Discount';
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
  discounts : Discount[];
  duplicateDiscount: Discount[];

  constructor(private formBuilder: FormBuilder, private discountService : DiscountService) { }

  ngOnInit(): void {
    this.addDiscountForm = this.formBuilder.group({
      name: ['', Validators.required],
      code: ['', Validators.required],
      percent: ['', Validators.required],
      from: ['', Validators.required],
      to: ['', [Validators.required]],
      status: ['', [Validators.required]],
    });
    this.discountService.getAllDiscounts().subscribe((res: Discount[]) => {
      console.log(res);
      this.discounts = res;
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

  handleDeleteModal(): any {
    //const id = this.route.snapshot.paramMap.get('id');
    // this.employeeService.deleteEmployee(id)
    //   .subscribe((res: any) => {
    //     console.log(res);
    //     if (res) {
    //       this.isDeleted = true;
    //     }
    //   });
  }

  handleEditModalOpen() {
    this.duplicateDiscount = { ...this.discounts };
  }

  handleEditDiscountModal() {
    console.log(this.duplicateDiscount);
    // this.employeeService.updateEmployee(this.duplicateEmployee)
    //   .subscribe((res: IEmployee) => {
    //     console.log(res);
    //     this.employee = res;
    //     if (res && res.id) {
    //       this.isUpdated = true;
    //       this.employee = res;
    //     }
    //   });
  }
}
