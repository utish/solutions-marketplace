import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SolutionsMarketplaceMarketplaceModule } from './marketplace/marketplace.module';
import { SolutionsMarketplaceConsumerModule } from './consumer/consumer.module';
import { SolutionsMarketplaceProducerModule } from './producer/producer.module';
import { SolutionsMarketplaceProblemModule } from './problem/problem.module';
import { SolutionsMarketplaceSolutionModule } from './solution/solution.module';
import { SolutionsMarketplaceRecipeModule } from './recipe/recipe.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        SolutionsMarketplaceMarketplaceModule,
        SolutionsMarketplaceConsumerModule,
        SolutionsMarketplaceProducerModule,
        SolutionsMarketplaceProblemModule,
        SolutionsMarketplaceSolutionModule,
        SolutionsMarketplaceRecipeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SolutionsMarketplaceEntityModule {}
