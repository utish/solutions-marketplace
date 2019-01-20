import { NgModule } from '@angular/core';

import { SolutionsMarketplaceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [SolutionsMarketplaceSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [SolutionsMarketplaceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class SolutionsMarketplaceSharedCommonModule {}
