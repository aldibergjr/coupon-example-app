import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { RouterExtensions } from "@nativescript/angular";
import { ModalDialogParams } from "@nativescript/angular";
@Component({
  selector: 'app-modal-root',
  templateUrl: './modal-root.component.html',
  styleUrls: ['./modal-root.component.css']
})
export class ModalRootComponent implements OnInit {

  constructor(private _routerExtensions: RouterExtensions,
    private _activeRoute: ActivatedRoute, private params: ModalDialogParams) { }

  ngOnInit(): void {
    if(this.params.context["request"] == "store")
      this._routerExtensions.navigate(["CreateStore"], { relativeTo: this._activeRoute });
    else if(this.params.context["request"] == "coupon")
      this._routerExtensions.navigate(["CreateCoupon"], { relativeTo: this._activeRoute });
  }

}
