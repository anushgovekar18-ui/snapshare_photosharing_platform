import { Routes } from '@angular/router';

export const routes: Routes = [
  { 
    path: 'login', 
    loadComponent: () => import('./components/login/login.component').then(c => c.LoginComponent)
  },
  { 
    path: 'signup', 
    loadComponent: () => import('./components/signup/signup.component').then(c => c.SignupComponent)
  },
  { 
    path: 'home', 
    loadComponent: () => import('./components/home/home.component').then(c => c.HomeComponent)
  },
  { 
    path: '', 
    redirectTo: '/home', 
    pathMatch: 'full' 
  }
];
