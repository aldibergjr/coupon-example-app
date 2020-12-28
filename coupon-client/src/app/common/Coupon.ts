export class Coupon{
    public nome: String;
    public id : String;
    public store_ref : String;
    public isActive : Boolean;
    
    constructor(nome: String, store_ref){
        this.nome = nome;
        this.store_ref = store_ref;
        this.isActive = true;
    }

}