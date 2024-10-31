import { ChangeDetectorRef, Component, EventEmitter, Output } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  menuValue = false;
  menu_icon = "bi bi-list";
  isMenuOpen = false;

  @Output() menuToggle = new EventEmitter<boolean>();

  constructor(private changeDetectorRef: ChangeDetectorRef) { }

  toggleMenu() {
    this.menuValue = !this.menuValue;
    this.menu_icon = this.menuValue ? 'bi bi-x' : 'bi bi-list';
    this.isMenuOpen = !this.isMenuOpen;
    this.menuToggle.emit(this.isMenuOpen);

    this.changeDetectorRef.detectChanges();
  }
  // menuValue: boolean = false;
  // menu_icon: string = 'bi bi-list';

  // openMenu() {
  //   this.menuValue = !this.menuValue;
  //   this.menu_icon = this.menuValue ? 'bi bi-x' : 'bi bi-list';
  //   this.menuToggle.emit(this.menuValue); 
  // }

  // closeMenu() {
  //   this.menuValue = false;
  //   this.menu_icon = 'bi bi-list';
  //   this.menuToggle.emit(this.menuValue); 
  // }
}
