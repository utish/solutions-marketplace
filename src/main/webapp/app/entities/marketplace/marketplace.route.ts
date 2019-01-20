import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Marketplace } from 'app/shared/model/marketplace.model';
import { MarketplaceService } from './marketplace.service';
import { MarketplaceComponent } from './marketplace.component';
import { MarketplaceDetailComponent } from './marketplace-detail.component';
import { MarketplaceUpdateComponent } from './marketplace-update.component';
import { MarketplaceDeletePopupComponent } from './marketplace-delete-dialog.component';
import { IMarketplace } from 'app/shared/model/marketplace.model';

@Injectable({ providedIn: 'root' })
export class MarketplaceResolve implements Resolve<IMarketplace> {
    constructor(private service: MarketplaceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Marketplace> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Marketplace>) => response.ok),
                map((marketplace: HttpResponse<Marketplace>) => marketplace.body)
            );
        }
        return of(new Marketplace());
    }
}

export const marketplaceRoute: Routes = [
    {
        path: 'marketplace',
        component: MarketplaceComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Marketplaces'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'marketplace/:id/view',
        component: MarketplaceDetailComponent,
        resolve: {
            marketplace: MarketplaceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Marketplaces'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'marketplace/new',
        component: MarketplaceUpdateComponent,
        resolve: {
            marketplace: MarketplaceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Marketplaces'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'marketplace/:id/edit',
        component: MarketplaceUpdateComponent,
        resolve: {
            marketplace: MarketplaceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Marketplaces'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const marketplacePopupRoute: Routes = [
    {
        path: 'marketplace/:id/delete',
        component: MarketplaceDeletePopupComponent,
        resolve: {
            marketplace: MarketplaceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Marketplaces'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
