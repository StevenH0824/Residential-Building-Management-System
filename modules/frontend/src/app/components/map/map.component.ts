import { Component, Input } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [],
  templateUrl: './map.component.html',
  styleUrl: './map.component.css'
})
export class MapComponent {
  map!: L.Map;

  @Input() buildings: { name: string; lat: number; lng: number }[] = [];

  ngOnInit(): void {
    this.initMap();
  }

  initMap(): void {
    const defaultLatitude = this.buildings[0]?.lat || 37.7749; // Fallback if no buildings
    const defaultLongitude = this.buildings[0]?.lng || -122.4194;

    this.map = L.map('map').setView([defaultLatitude, defaultLongitude], 13); // Set default view

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '© OpenStreetMap',
    }).addTo(this.map);

    // Add markers for each building
    this.addMarkers();
  }

  addMarkers(): void {
    this.buildings.forEach(building => {
      L.marker([building.lat, building.lng])
        .addTo(this.map)
        .bindPopup(building.name);
    });
  }

}
