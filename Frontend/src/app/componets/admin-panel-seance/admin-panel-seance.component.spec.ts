import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPanelSeanceComponent } from './admin-panel-seance.component';

describe('AdminPanelSeanceComponent', () => {
  let component: AdminPanelSeanceComponent;
  let fixture: ComponentFixture<AdminPanelSeanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPanelSeanceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminPanelSeanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
