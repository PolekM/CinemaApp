import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPanelButtonComponent } from './admin-panel-button.component';

describe('AdminPanelButtonComponent', () => {
  let component: AdminPanelButtonComponent;
  let fixture: ComponentFixture<AdminPanelButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPanelButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminPanelButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
