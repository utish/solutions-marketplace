import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SolutionsMarketplaceSharedModule } from 'app/shared';
import {
    SolutionComponent,
    SolutionDetailComponent,
    SolutionUpdateComponent,
    SolutionDeletePopupComponent,
    SolutionDeleteDialogComponent,
    solutionRoute,
    solutionPopupRoute
} from './';

const ENTITY_STATES = [...solutionRoute, ...solutionPopupRoute];

@NgModule({
    imports: [SolutionsMarketplaceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SolutionComponent,
        SolutionDetailComponent,
        SolutionUpdateComponent,
        SolutionDeleteDialogComponent,
        SolutionDeletePopupComponent
    ],
    entryComponents: [SolutionComponent, SolutionUpdateComponent, SolutionDeleteDialogComponent, SolutionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SolutionsMarketplaceSolutionModule {}
