import { CanActivateFn, Router } from '@angular/router';
import { routes } from '../app.routes';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem('authorization');
  const router = inject(Router)
  if(token){
    return true;
  }
  else{
    router.navigate(['/login']);
    return false;
  }
};
