import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IMarketplace } from 'app/shared/model/marketplace.model';
import { MarketplaceService } from './marketplace.service';

@Component({
    selector: 'jhi-marketplace-update',
    templateUrl: './marketplace-update.component.html'
})
export class MarketplaceUpdateComponent implements OnInit {
    marketplace: IMarketplace;
    isSaving: boolean;

    constructor(protected marketplaceService: MarketplaceService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ marketplace }) => {
            this.marketplace = marketplace;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.marketplace.id !== undefined) {
            this.subscribeToSaveResponse(this.marketplaceService.update(this.marketplace));
        } else {
            this.subscribeToSaveResponse(this.marketplaceService.create(this.marketplace));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMarketplace>>) {
        result.subscribe((res: HttpResponse<IMarketplace>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
