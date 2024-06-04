import { HttpInterceptorFn } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
export const httpErrorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(catchError((error: HttpErrorResponse)=>{

    if(error.status== 401){
      console.log("UnAuthorized " + error.error.code +" " + error.error.message + " " + error.error.errorTime);
    }
    if(error.status== 409){
      console.log("Duplicate " + error.error.code +" " + error.error.message + " " + error.error.errorTime);
    }
    if(error.status== 404){
      console.log("Not Found " + error.error.code +" " + error.error.message + " " + error.error.errorTime);
    }
    if(error.status== 500){
      console.log("Serwer Error " + error.error.code +" " + error.error.message + " " + error.error.errorTime);
    }
    return throwError(() => new Error(error.message));
  }))
};
