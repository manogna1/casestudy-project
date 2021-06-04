import { mergeAnalyzedFiles, templateJitUrl } from "@angular/compiler"
import { time } from "console"

export interface Flight{
flightNumber?:number;
airlineName?:string;
departTime?: Date;
arrivalTime?: Date;
duration?: string;
type?:string;
price?: string;
}


