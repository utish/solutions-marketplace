import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Consumer } from 'app/shared/model/consumer.model';
import { ConsumerService } from './consumer.service';
import { ConsumerComponent } from './consumer.component';
import { ConsumerDetailComponent } from './consumer-detail.component';
import { ConsumerUpdateComponent } from './consumer-update.component';
import { ConsumerDeletePopupComponent } from './consumer-delete-dialog.component';
import { IConsumer } from 'app/shared/model/consumer.model';

@Injectable({ providedIn: 'root' })
export class ConsumerResolve implements Resolve<IConsumer> {
    constructor(private service: ConsumerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Consumer> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Consumer>) => response.ok),
                map((consumer: HttpResponse<Consumer>) => consumer.body)
            );
        }
        return of(new Consumer());
    }
}

export const consumerRoute: Routes = [
    {
        path: 'consumer',
        component: ConsumerComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Consumers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'consumer/:id/view',
        component: ConsumerDetailComponent,
        resolve: {
            consumer: ConsumerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Consumers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'consumer/new',
        component: ConsumerUpdateComponent,
        resolve: {
            consumer: ConsumerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Consumers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'consumer/:id/edit',
        component: ConsumerUpdateComponent,
        resolve: {
            consumer: ConsumerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Consumers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const consumerPopupRoute: Routes = [
    {
        path: 'consumer/:id/delete',
        component: ConsumerDeletePopupComponent,
        resolve: {
            consumer: ConsumerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Consumers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
