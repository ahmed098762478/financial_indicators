import { Routes } from '@angular/router';
import { ImportComponentComponent } from './component/import-component/import-component.component';

export const routes: Routes = [
  { path: 'app', component: ImportComponentComponent } // Removed the leading '/'
];
