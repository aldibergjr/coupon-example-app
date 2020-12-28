import { Routes } from '@angular/router';

import { HomeComponent } from '@src/app/home/home.component';
import { CreateCouponComponent } from './forms/coupon/create-coupon/create-coupon.component';
import { CreateStoreComponent } from './forms/store/create-store/create-store.component';

export const routes: Routes = [
  {
      path: '',
      redirectTo: '/home',
      pathMatch: 'full',
  },
  {
      path: 'home',
      component: HomeComponent,
      children: [
        {
          path: 'CreateStore',
          component: CreateStoreComponent
        },
        {
          path: 'CreateCoupon',
          component : CreateCouponComponent
        }
      ]
  }
];
