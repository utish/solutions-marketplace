import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMarketplace } from 'app/shared/model/marketplace.model';
import { MarketplaceService } from './marketplace.service';

@Component({
    selector: 'jhi-marketplace-delete-dialog',
    templateUrl: './marketplace-delete-dialog.component.html'
})
export class MarketplaceDeleteDialogComponent {
    marketplace: IMarketplace;

    constructor(
        protected marketplaceService: MarketplaceService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.marketplaceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'marketplaceListModification',
                content: 'Deleted an marketplace'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-marketplace-delete-popup',
    template: ''
})
export class MarketplaceDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ marketplace }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MarketplaceDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.marketplace = marketplace;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
