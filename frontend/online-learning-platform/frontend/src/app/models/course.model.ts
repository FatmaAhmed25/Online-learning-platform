import { CourseStatus } from "./courseStatus";

export interface Course {
    id?: number; // Optional property
    instructorId: number;
    name: string;
    category: string; 
    duration : number; 
    rating : number;
    capacity : number;
    status : CourseStatus;
}