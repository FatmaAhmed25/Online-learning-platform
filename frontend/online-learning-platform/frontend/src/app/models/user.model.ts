// user.model.ts
import { UserRole } from '../models/userRole';
export interface User {
    id?: number; // Optional property
    name: string;
    email: string;
    password: string;
    affiliation?: string; // Optional property
    yearsOfExperience?:string;// Optional property
    bio?: string; // Optional property
    role: UserRole; 
  }
  