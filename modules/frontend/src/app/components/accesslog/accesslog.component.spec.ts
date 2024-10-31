import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccesslogComponent } from './accesslog.component';

describe('AccesslogComponent', () => {
  let component: AccesslogComponent;
  let fixture: ComponentFixture<AccesslogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccesslogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccesslogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
