import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { EventData, Page } from '@nativescript/core';
import { Coupon } from '../common/Coupon';
import { Store } from '../common/Store';
import { ModalDialogOptions, ModalDialogService } from "@nativescript/angular";
import {CommonService} from './../common/common.service'
import { ModalRootComponent } from '../modal/modal-root/modal-root.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  title = 'coupon-client';
  coupons :Coupon[] = [];
  stores :Store[] = [];
  data = [{heading: "dale", content: "salve"},{heading: "dale", content: "salve"}]
  constructor(page : Page, private service : CommonService ,private _modalService: ModalDialogService,
    private _vcRef: ViewContainerRef){
    page.actionBarHidden=true;
  }

  ngOnInit() {
    this.service.getAllStores()
    .subscribe((response) =>{
      response["data"].forEach((store :Store) => {
        this.stores.push(store);
      });

      this.service.getAllCoupons()
      .subscribe((response) => {
        response["data"].forEach((coupon :Coupon) => {
          coupon.store_ref = this.stores.find(store => store.id == coupon.store_ref).name;
          this.coupons.push(coupon)
        });
      })
    })
    
  }
  addStore(){
    const options: ModalDialogOptions = {
      viewContainerRef: this._vcRef,
      context: {request: "store"},
      fullscreen: true
    };
    this._modalService.showModal(ModalRootComponent, options)
            .then((result: string) => {
                console.log(result);
            });
  }
  addCoupon(){
    const options: ModalDialogOptions = {
      viewContainerRef: this._vcRef,
      context: {request: "coupon"},
      fullscreen: true
    };
    this._modalService.showModal(ModalRootComponent, options)
            .then((result: string) => {
                console.log(result);
            });
  }



}
