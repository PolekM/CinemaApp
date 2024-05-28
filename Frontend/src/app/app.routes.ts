import { Routes } from '@angular/router';
import { MovieComponent } from './componets/movie/movie.component';
import { AppUserComponent } from './componets/app-user/app-user.component';
import { LoginComponent } from './componets/login/login.component';
import { RegisterComponent } from './componets/register/register.component';
import { authGuard } from './guards/auth.guard';
import { authRoleGuard } from './guards/auth-role.guard';
import { loginAccessGuard } from './guards/login-access.guard';
import { MovieDetailsComponent } from './componets/movie-details/movie-details.component';
import { SeanceComponent } from './componets/seance/seance.component';
import { ReservationComponent } from './componets/reservation/reservation.component';
import { UserReservationComponent } from './componets/user-reservation/user-reservation.component';
import { UserReservationDetailsComponent } from './componets/user-reservation-details/user-reservation-details.component';
import { MovieSearchComponent } from './componets/movie-search/movie-search.component';

export const routes: Routes = [
    { 
        path:  '', 
        component: AppUserComponent
    },
    {
        path: 'movie', 
        component: MovieComponent
    },
    {
        path: 'movie/search',
          component: MovieSearchComponent
    },
    {
        path: 'movie/:id',
        component: MovieDetailsComponent
    },
    
    {
        path: 'user', 
        component: AppUserComponent,
        canActivate: [authGuard,authRoleGuard],
            data: {role: 'ROLE_ADMIN'}
     },
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [loginAccessGuard]
     },
    {
        path: 'register',
        component: RegisterComponent,
        canActivate: [loginAccessGuard]
    },
    {
        path: 'seance',
        component: SeanceComponent,
        
    },
    {
        path: 'reservation/:id',
        component: ReservationComponent,
        canActivate: [authGuard]
    },
    {
        path: 'ticket',
        component: UserReservationComponent,
        canActivate: [authGuard]
    },
    {
        path: 'ticket/:id',
        component: UserReservationDetailsComponent,
        canActivate: [authGuard]
    },
   
];
