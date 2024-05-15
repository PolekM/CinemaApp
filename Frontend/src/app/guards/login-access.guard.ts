import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const loginAccessGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem('authorization');
  const router = inject(Router)
  if(token){
    router.navigate(['/movie']);
    return false; 
  }
  else{
    return true;
  }
};
