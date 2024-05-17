export interface Notification {
    id: number;
    message: string;
    timestamp: Date; // Assuming you'll convert LocalDateTime to Date in your backend response or adjust it accordingly
    studentId: number;
  }
  