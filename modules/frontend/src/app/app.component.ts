import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./layout/header/header.component";
<<<<<<< HEAD
import { CommonModule } from '@angular/common';
import { FooterComponent } from './layout/footer/footer.component';
=======
import { FooterComponent } from "./layout/footer/footer.component";

>>>>>>> danaBranch

@Component({
  selector: 'app-root',
  standalone: true,
<<<<<<< HEAD
  imports: [CommonModule, RouterOutlet, HeaderComponent, FooterComponent],
=======
  imports: [RouterOutlet, HeaderComponent, FooterComponent],
>>>>>>> danaBranch
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'tutorial'
}



