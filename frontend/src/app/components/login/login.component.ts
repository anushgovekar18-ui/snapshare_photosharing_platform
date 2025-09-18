import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  isLoading: boolean = false;
  showForgotPasswordModal: boolean = false;

  constructor(private router: Router) {}

  onLogin() {
    this.isLoading = true;
    // Simulate login process
    setTimeout(() => {
      this.isLoading = false;
      console.log('Login attempted with:', this.email);
      // Navigate to home page after successful login
      this.router.navigate(['/home']);
    }, 2000);
  }

  onGoogleLogin() {
    console.log('Google login clicked');
    // Simulate Google login
    alert('Google login would be implemented here');
  }

  onForgotPassword() {
    this.showForgotPasswordModal = true;
  }

  closeForgotPasswordModal() {
    this.showForgotPasswordModal = false;
  }

  onSignUp() {
    this.router.navigate(['/signup']);
  }

  sendPasswordReset() {
    // Simulate password reset
    alert('Password reset link sent to your email!');
    this.closeForgotPasswordModal();
  }
}
