import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Producer } from 'app/shared/model/producer.model';
import { ProducerService } from './producer.service';
import { ProducerComponent } from './producer.component';
import { ProducerDetailComponent } from './producer-detail.component';
import { ProducerUpdateComponent } from './producer-update.component';
import { ProducerDeletePopupComponent } from './producer-delete-dialog.component';
import { IProducer } from 'app/shared/model/producer.model';

@Injectable({ providedIn: 'root' })
export class ProducerResolve implements Resolve<IProducer> {
    constructor(private service: ProducerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Producer> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Producer>) => response.ok),
                map((producer: HttpResponse<Producer>) => producer.body)
            );
        }
        return of(new Producer());
    }
}

export const producerRoute: Routes = [
    {
        path: 'producer',
        component: ProducerComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Producers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'producer/:id/view',
        component: ProducerDetailComponent,
        resolve: {
            producer: ProducerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Producers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'producer/new',
        component: ProducerUpdateComponent,
        resolve: {
            producer: ProducerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Producers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'producer/:id/edit',
        component: ProducerUpdateComponent,
        resolve: {
            producer: ProducerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Producers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const producerPopupRoute: Routes = [
    {
        path: 'producer/:id/delete',
        component: ProducerDeletePopupComponent,
        resolve: {
            producer: ProducerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Producers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
