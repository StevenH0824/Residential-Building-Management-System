import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaintenancerequestComponent } from './maintenance-request.component';

describe('MaintenancerequestComponent', () => {
  let component: MaintenancerequestComponent;
  let fixture: ComponentFixture<MaintenancerequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MaintenancerequestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MaintenancerequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
