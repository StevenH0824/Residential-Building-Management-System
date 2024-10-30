import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccessibleRoomsComponent } from './accessible-rooms.component';

describe('AccessibleRoomsComponent', () => {
  let component: AccessibleRoomsComponent;
  let fixture: ComponentFixture<AccessibleRoomsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccessibleRoomsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccessibleRoomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
