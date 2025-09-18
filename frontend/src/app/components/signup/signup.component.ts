import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="min-h-screen relative overflow-hidden bg-gradient-to-br from-purple-900 via-blue-900 to-indigo-900">
      <!-- Animated Background Elements -->
      <div class="absolute inset-0">
        <!-- Gradient Orbs -->
        <div class="absolute top-1/4 left-1/4 w-96 h-96 bg-gradient-to-r from-pink-500 to-violet-500 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-float"></div>
        <div class="absolute top-1/3 right-1/4 w-96 h-96 bg-gradient-to-r from-cyan-500 to-blue-500 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-float animation-delay-2000"></div>
        <div class="absolute bottom-1/4 left-1/3 w-96 h-96 bg-gradient-to-r from-emerald-500 to-teal-500 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-float animation-delay-4000"></div>
      </div>

      <!-- Main Content -->
      <div class="relative z-10 flex items-center justify-center min-h-screen p-4">
        <!-- Signup Card -->
        <div class="w-full max-w-md">
          <!-- Logo Section -->
          <div class="text-center mb-8">
            <div class="inline-flex items-center justify-center w-20 h-20 bg-gradient-to-r from-pink-500 to-violet-500 rounded-2xl mb-4 shadow-2xl">
              <svg class="w-10 h-10 text-white" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M4 3a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V5a2 2 0 00-2-2H4zm12 12H4l4-8 3 6 2-4 3 6z" clip-rule="evenodd"></path>
              </svg>
            </div>
            <h1 class="text-4xl font-bold text-white mb-2 font-display">Join SnapShare</h1>
            <p class="text-purple-200 text-lg">Create Your Account</p>
          </div>

          <!-- Signup Form -->
          <div class="glass rounded-3xl p-8 shadow-2xl border border-white/20">
            <form>
              <!-- Name Input -->
              <div class="mb-4">
                <label class="block text-sm font-medium text-white/80 mb-2">Full Name</label>
                <input
                  type="text"
                  class="w-full pl-4 pr-4 py-3 bg-white/10 border border-white/20 rounded-xl text-white placeholder-purple-200 focus:outline-none focus:ring-2 focus:ring-pink-500 focus:border-transparent transition-all duration-300 backdrop-blur-sm"
                  placeholder="Enter your full name"
                />
              </div>

              <!-- Email Input -->
              <div class="mb-4">
                <label class="block text-sm font-medium text-white/80 mb-2">Email Address</label>
                <input
                  type="email"
                  class="w-full pl-4 pr-4 py-3 bg-white/10 border border-white/20 rounded-xl text-white placeholder-purple-200 focus:outline-none focus:ring-2 focus:ring-pink-500 focus:border-transparent transition-all duration-300 backdrop-blur-sm"
                  placeholder="Enter your email"
                />
              </div>

              <!-- Password Input -->
              <div class="mb-6">
                <label class="block text-sm font-medium text-white/80 mb-2">Password</label>
                <input
                  type="password"
                  class="w-full pl-4 pr-4 py-3 bg-white/10 border border-white/20 rounded-xl text-white placeholder-purple-200 focus:outline-none focus:ring-2 focus:ring-pink-500 focus:border-transparent transition-all duration-300 backdrop-blur-sm"
                  placeholder="Create a password"
                />
              </div>

              <!-- Signup Button -->
              <button
                type="submit"
                class="w-full bg-gradient-to-r from-pink-500 to-violet-500 hover:from-pink-600 hover:to-violet-600 text-white font-bold py-3 px-6 rounded-xl transition-all duration-300 transform hover:scale-105 hover:shadow-2xl mb-6"
              >
                Create Account
              </button>
            </form>

            <!-- Footer Links -->
            <div class="text-center">
              <div class="text-white/60 text-sm">
                Already have an account? 
                <button 
                  (click)="goToLogin()"
                  class="text-pink-400 hover:text-pink-300 ml-1 font-semibold transition-colors duration-300 underline-offset-4 hover:underline"
                >
                  Sign in here
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .animation-delay-2000 {
      animation-delay: 2s;
    }
    .animation-delay-4000 {
      animation-delay: 4s;
    }
    .glass {
      background: linear-gradient(135deg, rgba(255, 255, 255, 0.1), rgba(255, 255, 255, 0.05));
      backdrop-filter: blur(20px);
      border: 1px solid rgba(255, 255, 255, 0.2);
      box-shadow: 0 25px 50px rgba(0, 0, 0, 0.25);
    }
  `]
})
export class SignupComponent {
  constructor(private router: Router) {}
  
  goToLogin() {
    this.router.navigate(['/login']);
  }
}
