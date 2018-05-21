import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BrewsblogRfbLocationModule } from './rfb-location/rfb-location.module';
import { BrewsblogRfbEventModule } from './rfb-event/rfb-event.module';
import { BrewsblogRfbUserModule } from './rfb-user/rfb-user.module';
import { BrewsblogRfbEventAttendanceModule } from './rfb-event-attendance/rfb-event-attendance.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BrewsblogRfbLocationModule,
        BrewsblogRfbEventModule,
        BrewsblogRfbUserModule,
        BrewsblogRfbEventAttendanceModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BrewsblogEntityModule {}
