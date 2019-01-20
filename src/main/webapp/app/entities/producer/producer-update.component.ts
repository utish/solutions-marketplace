import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProducer } from 'app/shared/model/producer.model';
import { ProducerService } from './producer.service';
import { IMarketplace } from 'app/shared/model/marketplace.model';
import { MarketplaceService } from 'app/entities/marketplace';

@Component({
    selector: 'jhi-producer-update',
    templateUrl: './producer-update.component.html'
})
export class ProducerUpdateComponent implements OnInit {
    producer: IProducer;
    isSaving: boolean;

    marketplaces: IMarketplace[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected producerService: ProducerService,
        protected marketplaceService: MarketplaceService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ producer }) => {
            this.producer = producer;
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
        if (this.producer.id !== undefined) {
            this.subscribeToSaveResponse(this.producerService.update(this.producer));
        } else {
            this.subscribeToSaveResponse(this.producerService.create(this.producer));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProducer>>) {
        result.subscribe((res: HttpResponse<IProducer>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
