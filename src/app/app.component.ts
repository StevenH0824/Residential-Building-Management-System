// HTTPCLIENT is depreciated, need to use provideHttpClient, added to app.config.ts
import { HttpClient} from '@angular/common/http';
import { Component, Injectable} from '@angular/core';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})


/*
10/13/24 
- Front End is working with the backend inside of the same project.  
- The title is being displayed in angular which we are getting from the spring boot application.
- https://www.youtube.com/watch?v=Z7L0BeAZE0Y (Tutorial)
- The tutorial showed something that's not appearing in the network tab, the message is still being displayed when you go directly to the link.
- You won't be able to replicate it the same way it was shown because it has some depericated features, I'll work on it again on monday.
*/

@Injectable({ providedIn: 'root' }) // decorator that marks the class as a service that can be injected into other components and services. 
export class AppComponent {
  title = 'no title';

  constructor(private http: HttpClient) {
    this.http.get("/api/hello").subscribe({
      next: (response: any) => {
        this.title = response.message;
        console.log(this.title);
      },
      error: (error) => {
        console.error('Error fetching data:', error);
      }
    });
    // This allows us to make http request within services. This is a get http request but this would be the same setup you would use
    // for post/put/delete/etc. 

  }
}
