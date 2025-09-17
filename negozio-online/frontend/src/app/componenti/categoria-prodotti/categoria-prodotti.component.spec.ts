import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriaProdottiComponent } from './categoria-prodotti.component';

describe('CategoriaProdottiComponent', () => {
  let component: CategoriaProdottiComponent;
  let fixture: ComponentFixture<CategoriaProdottiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CategoriaProdottiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoriaProdottiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
