import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { User } from '../models/user.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {
  constructor(private adminService: AdminService) { }

  users: User[] = [];
  selectedUser: User | null = null;
  ngOnInit(): void {
  
    this.fetchUsers();

  }
  fetchUsers(): void {
    this.adminService.getUsers().subscribe(
      (users: User[]) => {
        this.users = users;
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    );
  }

  deleteUser(userId: number): void {
    this.adminService.deleteUser(userId).subscribe(
      () => {
        console.log('User deleted successfully');
        this.fetchUsers();
      },
      (error) => {
        console.error('Error deleting user:', error);
      }
    );
  }

  editUser(user: User): void {
    this.adminService.editUser(user).subscribe(
      () => {
        console.log('User edited successfully');
        this.fetchUsers();
      },
      (error) => {
        console.error('Error editing user:', error);
      }
    );
  }
  


}
