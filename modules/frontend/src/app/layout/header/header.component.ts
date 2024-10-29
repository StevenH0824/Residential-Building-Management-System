import { Component, EventEmitter, Output } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  @Output() menuToggle = new EventEmitter<boolean>(); 
  menuValue: boolean = false;
  menu_icon: string = 'bi bi-list';

  openMenu() {
    this.menuValue = !this.menuValue;
    this.menu_icon = this.menuValue ? 'bi bi-x' : 'bi bi-list';
    this.menuToggle.emit(this.menuValue); 
  }
  
  closeMenu() {
    this.menuValue = false;
    this.menu_icon = 'bi bi-list';
    this.menuToggle.emit(this.menuValue); 
  }
}
