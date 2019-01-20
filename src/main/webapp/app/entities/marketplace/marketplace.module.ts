import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SolutionsMarketplaceSharedModule } from 'app/shared';
import {
    MarketplaceComponent,
    MarketplaceDetailComponent,
    MarketplaceUpdateComponent,
    MarketplaceDeletePopupComponent,
    MarketplaceDeleteDialogComponent,
    marketplaceRoute,
    marketplacePopupRoute
} from './';

const ENTITY_STATES = [...marketplaceRoute, ...marketplacePopupRoute];

@NgModule({
    imports: [SolutionsMarketplaceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MarketplaceComponent,
        MarketplaceDetailComponent,
        MarketplaceUpdateComponent,
        MarketplaceDeleteDialogComponent,
        MarketplaceDeletePopupComponent
    ],
    entryComponents: [MarketplaceComponent, MarketplaceUpdateComponent, MarketplaceDeleteDialogComponent, MarketplaceDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SolutionsMarketplaceMarketplaceModule {}
