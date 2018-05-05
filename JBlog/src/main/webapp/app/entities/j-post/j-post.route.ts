import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JPostComponent } from './j-post.component';
import { JPostDetailComponent } from './j-post-detail.component';
import { JPostPopupComponent } from './j-post-dialog.component';
import { JPostDeletePopupComponent } from './j-post-delete-dialog.component';

export const jPostRoute: Routes = [
    {
        path: 'j-post',
        component: JPostComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JPosts'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'j-post/:id',
        component: JPostDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JPosts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jPostPopupRoute: Routes = [
    {
        path: 'j-post-new',
        component: JPostPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JPosts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'j-post/:id/edit',
        component: JPostPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JPosts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'j-post/:id/delete',
        component: JPostDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JPosts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
