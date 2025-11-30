export interface Request {
    requestId?:number;
    agroChemicalId?:number;
    userId?:number;
    cropId:number;
    quantity:number;
    status:string;
    requestDate:string;
}
