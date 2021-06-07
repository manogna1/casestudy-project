export interface Discount {
    id?:number;
    name?: string;
    code?: string;
    percent?: number;
    from?: Date;
    to?: Date;
    status?: string;
   }