import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMarketplace } from 'app/shared/model/marketplace.model';

@Component({
    selector: 'jhi-marketplace-detail',
    templateUrl: './marketplace-detail.component.html'
})
export class MarketplaceDetailComponent implements OnInit {
    marketplace: IMarketplace;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ marketplace }) => {
            this.marketplace = marketplace;
        });
    }

    previousState() {
        window.history.back();
    }
}
