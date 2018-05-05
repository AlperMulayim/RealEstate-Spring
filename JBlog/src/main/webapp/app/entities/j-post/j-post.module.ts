import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JBlogSharedModule } from '../../shared';
import {
    JPostService,
    JPostPopupService,
    JPostComponent,
    JPostDetailComponent,
    JPostDialogComponent,
    JPostPopupComponent,
    JPostDeletePopupComponent,
    JPostDeleteDialogComponent,
    jPostRoute,
    jPostPopupRoute,
} from './';

const ENTITY_STATES = [
    ...jPostRoute,
    ...jPostPopupRoute,
];

@NgModule({
    imports: [
        JBlogSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        JPostComponent,
        JPostDetailComponent,
        JPostDialogComponent,
        JPostDeleteDialogComponent,
        JPostPopupComponent,
        JPostDeletePopupComponent,
    ],
    entryComponents: [
        JPostComponent,
        JPostDialogComponent,
        JPostPopupComponent,
        JPostDeleteDialogComponent,
        JPostDeletePopupComponent,
    ],
    providers: [
        JPostService,
        JPostPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JBlogJPostModule {}
