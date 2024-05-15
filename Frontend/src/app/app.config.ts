import { ApplicationConfig, Input, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { routes } from './app.routes';
import { BrowserModule } from '@angular/platform-browser';
import { authInterceptor } from './interceptor/auth.interceptor';
import { DatePipe } from '@angular/common';





export const appConfig: ApplicationConfig = {
    providers: [provideRouter(routes),
    provideHttpClient(withInterceptors([authInterceptor])),
    importProvidersFrom(BrowserModule),
    DatePipe,

    
    ]
};
