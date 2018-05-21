import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BrewsblogSharedModule } from '../../shared';
import {
    RfbEventService,
    RfbEventPopupService,
    RfbEventComponent,
    RfbEventDetailComponent,
    RfbEventDialogComponent,
    RfbEventPopupComponent,
    RfbEventDeletePopupComponent,
    RfbEventDeleteDialogComponent,
    rfbEventRoute,
    rfbEventPopupRoute,
    RfbEventResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...rfbEventRoute,
    ...rfbEventPopupRoute,
];

@NgModule({
    imports: [
        BrewsblogSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        RfbEventComponent,
        RfbEventDetailComponent,
        RfbEventDialogComponent,
        RfbEventDeleteDialogComponent,
        RfbEventPopupComponent,
        RfbEventDeletePopupComponent,
    ],
    entryComponents: [
        RfbEventComponent,
        RfbEventDialogComponent,
        RfbEventPopupComponent,
        RfbEventDeleteDialogComponent,
        RfbEventDeletePopupComponent,
    ],
    providers: [
        RfbEventService,
        RfbEventPopupService,
        RfbEventResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BrewsblogRfbEventModule {}
