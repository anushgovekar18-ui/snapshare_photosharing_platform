import { Component, OnInit, Renderer2 } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

interface UserProfile {
  username: string;
  fullName: string;
  bio: string;
  avatar: string;
  verified: boolean;
  followers: number;
  following: number;
  posts: number;
  website?: string;
  location?: string;
}

interface ProfilePost {
  id: number;
  imageUrl: string;
  likes: number;
  comments: number;
  caption: string;
}

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  activeTab: string = 'posts';
  theme: string = 'dark';

  userProfile: UserProfile = {
    username: 'your_username',
    fullName: 'Your Name',
    bio: 'Photography enthusiast 📸 | Nature lover 🌿 | Digital creator ✨',
    avatar: 'https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=150&h=150&fit=crop&crop=face',
    verified: true,
    followers: 2847,
    following: 312,
    posts: 156,
    website: 'www.yourwebsite.com',
    location: 'New York, NY'
  };

  userPosts: ProfilePost[] = [];
  savedPosts: ProfilePost[] = [];
  likedPosts: ProfilePost[] = [];

  constructor(
    private router: Router,
    private renderer: Renderer2
  ) {}

  ngOnInit() {
    this.loadTheme();
    this.generateProfilePosts();
  }

  loadTheme() {
    if (typeof window !== 'undefined' && window.localStorage) {
      this.theme = localStorage.getItem('theme') || 'dark';
    }
    this.applyTheme();
  }

  applyTheme() {
    if (typeof document !== 'undefined') {
      const root = document.documentElement;
      if (this.theme === 'dark') {
        this.renderer.addClass(root, 'dark');
        this.renderer.removeClass(root, 'light');
      } else {
        this.renderer.addClass(root, 'light');
        this.renderer.removeClass(root, 'dark');
      }
    }
  }

  generateProfilePosts() {
    const sampleImages = [
      'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=400&h=400&fit=crop',
      'https://images.unsplash.com/photo-1469474968028-56623f02e42e?w=400&h=400&fit=crop',
      'https://images.unsplash.com/photo-1500534314209-a25ddb2bd429?w=400&h=400&fit=crop',
      'https://images.unsplash.com/photo-1472214103451-9374bd1c798e?w=400&h=400&fit=crop',
      'https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=400&h=400&fit=crop',
      'https://images.unsplash.com/photo-1448375240586-882707db888b?w=400&h=400&fit=crop',
      'https://images.unsplash.com/photo-1471357674240-e1a485acb3e1?w=400&h=400&fit=crop',
      'https://images.unsplash.com/photo-1475924156734-496f6cac6ec1?w=400&h=400&fit=crop',
      'https://images.unsplash.com/photo-1493246507139-91e8fad9978e?w=400&h=400&fit=crop'
    ];

    const captions = [
      'Beautiful sunset at the mountains 🌄',
      'Nature\'s masterpiece ✨',
      'Peaceful morning vibes 🌅',
      'Adventure awaits! 🏔️',
      'Golden hour magic ✨',
      'Serenity found in nature 🍃',
      'Capturing moments that matter 📸',
      'Wanderlust calling 🌍',
      'Perfect lighting, perfect moment ☀️'
    ];

    // Generate user posts
    for (let i = 0; i < 12; i++) {
      this.userPosts.push({
        id: i + 1,
        imageUrl: sampleImages[i % sampleImages.length],
        likes: Math.floor(Math.random() * 500) + 50,
        comments: Math.floor(Math.random() * 50) + 5,
        caption: captions[i % captions.length]
      });
    }

    // Generate saved posts (subset of different images)
    for (let i = 0; i < 8; i++) {
      this.savedPosts.push({
        id: i + 100,
        imageUrl: sampleImages[(i + 3) % sampleImages.length],
        likes: Math.floor(Math.random() * 300) + 30,
        comments: Math.floor(Math.random() * 30) + 3,
        caption: captions[(i + 2) % captions.length]
      });
    }

    // Generate liked posts (subset of different images)
    for (let i = 0; i < 15; i++) {
      this.likedPosts.push({
        id: i + 200,
        imageUrl: sampleImages[(i + 5) % sampleImages.length],
        likes: Math.floor(Math.random() * 800) + 100,
        comments: Math.floor(Math.random() * 80) + 10,
        caption: captions[(i + 4) % captions.length]
      });
    }
  }

  setActiveTab(tab: string) {
    this.activeTab = tab;
  }

  getCurrentPosts(): ProfilePost[] {
    switch (this.activeTab) {
      case 'posts':
        return this.userPosts;
      case 'saved':
        return this.savedPosts;
      case 'liked':
        return this.likedPosts;
      default:
        return this.userPosts;
    }
  }

  goBack() {
    this.router.navigate(['/home']);
  }

  onEditProfile() {
    console.log('Edit profile functionality');
  }

  onShareProfile() {
    console.log('Share profile functionality');
  }

  onPostClick(post: ProfilePost) {
    console.log('Opening post:', post.id);
  }
}