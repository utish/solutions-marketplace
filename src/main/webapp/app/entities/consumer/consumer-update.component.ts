import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IConsumer } from 'app/shared/model/consumer.model';
import { ConsumerService } from './consumer.service';
import { IMarketplace } from 'app/shared/model/marketplace.model';
import { MarketplaceService } from 'app/entities/marketplace';

@Component({
    selector: 'jhi-consumer-update',
    templateUrl: './consumer-update.component.html'
})
export class ConsumerUpdateComponent implements OnInit {
    consumer: IConsumer;
    isSaving: boolean;

    marketplaces: IMarketplace[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected consumerService: ConsumerService,
        protected marketplaceService: MarketplaceService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ consumer }) => {
            this.consumer = consumer;
        });
        this.marketplaceService.query().subscribe(
            (res: HttpResponse<IMarketplace[]>) => {
                this.marketplaces = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.consumer.id !== undefined) {
            this.subscribeToSaveResponse(this.consumerService.update(this.consumer));
        } else {
            this.subscribeToSaveResponse(this.consumerService.create(this.consumer));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IConsumer>>) {
        result.subscribe((res: HttpResponse<IConsumer>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackMarketplaceById(index: number, item: IMarketplace) {
        return item.id;
    }
}
