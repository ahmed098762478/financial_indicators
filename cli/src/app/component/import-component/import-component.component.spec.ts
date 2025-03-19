import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportComponentComponent } from './import-component.component';

describe('ImportComponentComponent', () => {
  let component: ImportComponentComponent;
  let fixture: ComponentFixture<ImportComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImportComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImportComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
