import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SolutionsMarketplaceSharedModule } from 'app/shared';
import {
    ProducerComponent,
    ProducerDetailComponent,
    ProducerUpdateComponent,
    ProducerDeletePopupComponent,
    ProducerDeleteDialogComponent,
    producerRoute,
    producerPopupRoute
} from './';

const ENTITY_STATES = [...producerRoute, ...producerPopupRoute];

@NgModule({
    imports: [SolutionsMarketplaceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProducerComponent,
        ProducerDetailComponent,
        ProducerUpdateComponent,
        ProducerDeleteDialogComponent,
        ProducerDeletePopupComponent
    ],
    entryComponents: [ProducerComponent, ProducerUpdateComponent, ProducerDeleteDialogComponent, ProducerDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SolutionsMarketplaceProducerModule {}
