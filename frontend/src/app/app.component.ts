import { Component } from '@angular/core';
import { Router, NavigationEnd, RouterOutlet } from '@angular/router';
import { filter } from 'rxjs/operators';
import { SidebarComponent } from './sidebar/sidebar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  imports: [SidebarComponent, RouterOutlet],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  showSidebar = true;

  constructor(private router: Router) {
    // Écoutez les événements de navigation pour ajuster l'affichage de la sidebar.
    this.router.events
      .pipe(
        filter((event): event is NavigationEnd => event instanceof NavigationEnd)
      )
      .subscribe((event: NavigationEnd) => {
        // Ne pas afficher la sidebar sur la page de login
        this.showSidebar = event.url !== '/login';
      });
  }
}
