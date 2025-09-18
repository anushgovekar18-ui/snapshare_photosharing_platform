import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

interface PhotoPost {
  id: number;
  user: {
    username: string;
    avatar: string;
    verified: boolean;
  };
  image: string;
  caption: string;
  likes: number;
  comments: number;
  timestamp: string;
  liked: boolean;
  tags: string[];
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  searchQuery: string = '';
  showUploadModal: boolean = false;
  posts: PhotoPost[] = [];
  trendingTags: string[] = [
    '#photography', '#nature', '#sunset', '#travel', '#art', 
    '#portrait', '#landscape', '#street', '#urban', '#minimal'
  ];
  
  suggestedUsers = [
    { username: 'alex_photo', avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=400&h=400&fit=crop&crop=face', followers: '12.5k' },
    { username: 'sarah_lens', avatar: 'https://images.unsplash.com/photo-1494790108755-2616b612b786?w=400&h=400&fit=crop&crop=face', followers: '8.3k' },
    { username: 'mike_captures', avatar: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=400&fit=crop&crop=face', followers: '15.7k' },
    { username: 'emma_shots', avatar: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=400&h=400&fit=crop&crop=face', followers: '9.1k' }
  ];

  constructor(private router: Router) {}

  ngOnInit() {
    this.generateSamplePosts();
  }

  generateSamplePosts() {
    const sampleImages = [
      'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=800&h=600&fit=crop',
      'https://images.unsplash.com/photo-1469474968028-56623f02e42e?w=800&h=600&fit=crop',
      'https://images.unsplash.com/photo-1500534314209-a25ddb2bd429?w=800&h=600&fit=crop',
      'https://images.unsplash.com/photo-1472214103451-9374bd1c798e?w=800&h=600&fit=crop',
      'https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=800&h=600&fit=crop',
      'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=800&h=600&fit=crop',
      'https://images.unsplash.com/photo-1448375240586-882707db888b?w=800&h=600&fit=crop',
      'https://images.unsplash.com/photo-1471357674240-e1a485acb3e1?w=800&h=600&fit=crop'
    ];

    const users = [
      { username: 'nature_lover', avatar: 'https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100&h=100&fit=crop&crop=face', verified: true },
      { username: 'wanderlust_jo', avatar: 'https://images.unsplash.com/photo-1494790108755-2616b612b786?w=100&h=100&fit=crop&crop=face', verified: false },
      { username: 'photo_enthusiast', avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=100&h=100&fit=crop&crop=face', verified: true },
      { username: 'sunset_chaser', avatar: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=100&h=100&fit=crop&crop=face', verified: false },
      { username: 'art_captures', avatar: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=100&h=100&fit=crop&crop=face', verified: true }
    ];

    const captions = [
      'Golden hour magic in the mountains 🌄 Nothing beats this view!',
      'Lost in the beauty of nature. Sometimes you need to disconnect to reconnect.',
      'Every sunrise is a new beginning. What story will today tell?',
      'Chasing waterfalls and finding peace in the wilderness.',
      'The forest whispers secrets only the heart can understand.',
      'City lights dancing on the water. Urban beauty at its finest.',
      'Minimalism meets nature. Less is indeed more.',
      'Adventure awaits around every corner. Keep exploring! 🏔️'
    ];

    this.posts = Array.from({ length: 8 }, (_, i) => ({
      id: i + 1,
      user: users[i % users.length],
      image: sampleImages[i],
      caption: captions[i],
      likes: Math.floor(Math.random() * 1000) + 50,
      comments: Math.floor(Math.random() * 50) + 5,
      timestamp: this.getRandomTimestamp(),
      liked: Math.random() > 0.5,
      tags: this.getRandomTags()
    }));
  }

  getRandomTimestamp(): string {
    const hours = Math.floor(Math.random() * 24) + 1;
    return `${hours}h`;
  }

  getRandomTags(): string[] {
    const shuffled = [...this.trendingTags].sort(() => 0.5 - Math.random());
    return shuffled.slice(0, Math.floor(Math.random() * 3) + 1);
  }

  toggleLike(post: PhotoPost) {
    post.liked = !post.liked;
    post.likes += post.liked ? 1 : -1;
  }

  openUploadModal() {
    this.showUploadModal = true;
  }

  closeUploadModal() {
    this.showUploadModal = false;
  }

  onSearch() {
    console.log('Searching for:', this.searchQuery);
    // Implement search functionality
  }

  onNotifications() {
    console.log('Opening notifications');
    // Implement notifications
  }

  onProfile() {
    console.log('Opening profile');
    // Navigate to profile
  }

  followUser(username: string) {
    console.log('Following user:', username);
    // Implement follow functionality
  }

  onUploadPhoto() {
    console.log('Upload photo functionality');
    this.closeUploadModal();
    // Implement photo upload
  }

  onLogout() {
    // Clear any stored user data/tokens here
    console.log('Logging out...');
    this.router.navigate(['/login']);
  }

  goHome() {
    // Refresh the current page or scroll to top
    window.scrollTo(0, 0);
  }
}
