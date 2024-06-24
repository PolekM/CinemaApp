import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPanelMovieComponent } from './admin-panel-movie.component';

describe('AdminPanelMovieComponent', () => {
  let component: AdminPanelMovieComponent;
  let fixture: ComponentFixture<AdminPanelMovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPanelMovieComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminPanelMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
