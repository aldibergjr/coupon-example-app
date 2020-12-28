import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from '@src/app/app-routing.module';
import { AppComponent } from '@src/app/app.component';
import { HomeComponent } from '@src/app/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonService } from '@src/app/common/common.service';
import { registerElement } from '@nativescript/angular';
import { ModalRootComponent } from '@src/app/modal/modal-root/modal-root.component';
import { CreateStoreComponent } from '@src/app/forms/store/create-store/create-store.component';
import { CreateCouponComponent } from '@src/app/forms/coupon/create-coupon/create-coupon.component';
registerElement('Fab',
() => require('@nstudio/nativescript-floatingactionbutton').Fab)
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ModalRootComponent,
    CreateStoreComponent,
    CreateCouponComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [CommonService,],
  bootstrap: [AppComponent]
})
export class AppModule { }
