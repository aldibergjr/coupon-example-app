import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptModule } from '@nativescript/angular';
import { AppRoutingModule } from '@src/app/app-routing.module';
import { AppComponent } from '@src/app/app.component';
import { HomeComponent } from '@src/app/home/home.component';
import { CommonService } from '@src/app/common/common.service';
import { registerElement } from '@nativescript/angular';
registerElement('Fab',
() => require('@nstudio/nativescript-floatingactionbutton').Fab)
// Uncomment and add to NgModule imports if you need to use two-way binding and/or HTTP wrapper
import { NativeScriptFormsModule, NativeScriptHttpClientModule } from '@nativescript/angular';
import { ModalRootComponent } from '@src/app/modal/modal-root/modal-root.component';
import { CreateStoreComponent } from '@src/app/forms/store/create-store/create-store.component';
import { CreateCouponComponent } from '@src/app/forms/coupon/create-coupon/create-coupon.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ModalRootComponent,
    CreateStoreComponent,
    CreateCouponComponent,
  ],
  imports: [
    NativeScriptModule,
    AppRoutingModule,
    NativeScriptHttpClientModule,
    NativeScriptFormsModule,
  ],
  providers: [CommonService,],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
