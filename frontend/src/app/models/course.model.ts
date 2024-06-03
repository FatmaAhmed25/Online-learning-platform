import { CourseStatus } from "./courseStatus";
import { Review  }from "./review.model"
export interface Course {
    id?: number; // Optional property
    instructorId: number;
    name: string;
    category: string; 
    duration : number; 
    rating : number;
    capacity : number;
    status : CourseStatus;
    numberOfEnrolledStudents:number;
    reviews?: Review[];
    showReviewBox?: boolean; 
}