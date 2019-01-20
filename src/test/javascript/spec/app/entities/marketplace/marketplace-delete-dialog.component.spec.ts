/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SolutionsMarketplaceTestModule } from '../../../test.module';
import { MarketplaceDeleteDialogComponent } from 'app/entities/marketplace/marketplace-delete-dialog.component';
import { MarketplaceService } from 'app/entities/marketplace/marketplace.service';

describe('Component Tests', () => {
    describe('Marketplace Management Delete Component', () => {
        let comp: MarketplaceDeleteDialogComponent;
        let fixture: ComponentFixture<MarketplaceDeleteDialogComponent>;
        let service: MarketplaceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SolutionsMarketplaceTestModule],
                declarations: [MarketplaceDeleteDialogComponent]
            })
                .overrideTemplate(MarketplaceDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MarketplaceDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MarketplaceService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
