import { User } from "./user.model";

export interface Feedback {
    feedbackId?:number;
    userId?:number;
    feedbackText:string;
    date:Date;
    user:User;
}
