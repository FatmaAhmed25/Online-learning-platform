// enrollment.model.ts
export interface Enrollment {
    id: number;
    courseId: number;
    studentId: number;
    status: string;
    courseName?: string; // Include courseName if you plan to use it in the UI
  }
  