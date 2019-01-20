import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Solution } from 'app/shared/model/solution.model';
import { SolutionService } from './solution.service';
import { SolutionComponent } from './solution.component';
import { SolutionDetailComponent } from './solution-detail.component';
import { SolutionUpdateComponent } from './solution-update.component';
import { SolutionDeletePopupComponent } from './solution-delete-dialog.component';
import { ISolution } from 'app/shared/model/solution.model';

@Injectable({ providedIn: 'root' })
export class SolutionResolve implements Resolve<ISolution> {
    constructor(private service: SolutionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Solution> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Solution>) => response.ok),
                map((solution: HttpResponse<Solution>) => solution.body)
            );
        }
        return of(new Solution());
    }
}

export const solutionRoute: Routes = [
    {
        path: 'solution',
        component: SolutionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Solutions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'solution/:id/view',
        component: SolutionDetailComponent,
        resolve: {
            solution: SolutionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Solutions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'solution/new',
        component: SolutionUpdateComponent,
        resolve: {
            solution: SolutionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Solutions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'solution/:id/edit',
        component: SolutionUpdateComponent,
        resolve: {
            solution: SolutionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Solutions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const solutionPopupRoute: Routes = [
    {
        path: 'solution/:id/delete',
        component: SolutionDeletePopupComponent,
        resolve: {
            solution: SolutionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Solutions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
