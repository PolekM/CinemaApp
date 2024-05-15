import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const storedHeaders = localStorage.getItem('authorization');
  if(storedHeaders){
    req = req.clone({
      setHeaders: {
        Authorization: storedHeaders 
      }
    });

    }

  return next(req);
};
