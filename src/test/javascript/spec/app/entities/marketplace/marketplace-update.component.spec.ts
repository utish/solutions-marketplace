/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SolutionsMarketplaceTestModule } from '../../../test.module';
import { MarketplaceUpdateComponent } from 'app/entities/marketplace/marketplace-update.component';
import { MarketplaceService } from 'app/entities/marketplace/marketplace.service';
import { Marketplace } from 'app/shared/model/marketplace.model';

describe('Component Tests', () => {
    describe('Marketplace Management Update Component', () => {
        let comp: MarketplaceUpdateComponent;
        let fixture: ComponentFixture<MarketplaceUpdateComponent>;
        let service: MarketplaceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SolutionsMarketplaceTestModule],
                declarations: [MarketplaceUpdateComponent]
            })
                .overrideTemplate(MarketplaceUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MarketplaceUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MarketplaceService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Marketplace(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.marketplace = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Marketplace();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.marketplace = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
