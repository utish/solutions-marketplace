import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SolutionsMarketplaceSharedModule } from 'app/shared';
import {
    ConsumerComponent,
    ConsumerDetailComponent,
    ConsumerUpdateComponent,
    ConsumerDeletePopupComponent,
    ConsumerDeleteDialogComponent,
    consumerRoute,
    consumerPopupRoute
} from './';

const ENTITY_STATES = [...consumerRoute, ...consumerPopupRoute];

@NgModule({
    imports: [SolutionsMarketplaceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConsumerComponent,
        ConsumerDetailComponent,
        ConsumerUpdateComponent,
        ConsumerDeleteDialogComponent,
        ConsumerDeletePopupComponent
    ],
    entryComponents: [ConsumerComponent, ConsumerUpdateComponent, ConsumerDeleteDialogComponent, ConsumerDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SolutionsMarketplaceConsumerModule {}
