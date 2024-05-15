import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authRoleGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem('userRole');
  const router = inject(Router)
  if(token ==="ROLE_ADMIN"){
    return true;
  }
  else{
    router.navigate(['/login']);
    return false;
  }
 
};
