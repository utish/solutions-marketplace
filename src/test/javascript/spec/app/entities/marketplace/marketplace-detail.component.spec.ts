/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SolutionsMarketplaceTestModule } from '../../../test.module';
import { MarketplaceDetailComponent } from 'app/entities/marketplace/marketplace-detail.component';
import { Marketplace } from 'app/shared/model/marketplace.model';

describe('Component Tests', () => {
    describe('Marketplace Management Detail Component', () => {
        let comp: MarketplaceDetailComponent;
        let fixture: ComponentFixture<MarketplaceDetailComponent>;
        const route = ({ data: of({ marketplace: new Marketplace(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SolutionsMarketplaceTestModule],
                declarations: [MarketplaceDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MarketplaceDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MarketplaceDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.marketplace).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
