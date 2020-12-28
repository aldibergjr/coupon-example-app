import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import {Coupon} from './Coupon'
@Injectable({
    providedIn: 'root', // <---- Adiciona isto ao serviço
  })
export class CommonService{
    constructor(private http: HttpClient){}
    getAllCoupons(){
        let url = 'http://10.0.2.2:8080/getCoupons';
        return this.http.get(url)
    }
    getAllStores(){
        let url = 'http://10.0.2.2:8080/getStores';
        return this.http.get(url)
    }
    createCoupon(coupon : Coupon){
        let url = 'http://localhost:8080/coupon/create';
        return this.http.post(url, JSON.stringify(coupon));
    }
}