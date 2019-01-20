import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMarketplace } from 'app/shared/model/marketplace.model';

type EntityResponseType = HttpResponse<IMarketplace>;
type EntityArrayResponseType = HttpResponse<IMarketplace[]>;

@Injectable({ providedIn: 'root' })
export class MarketplaceService {
    public resourceUrl = SERVER_API_URL + 'api/marketplaces';

    constructor(protected http: HttpClient) {}

    create(marketplace: IMarketplace): Observable<EntityResponseType> {
        return this.http.post<IMarketplace>(this.resourceUrl, marketplace, { observe: 'response' });
    }

    update(marketplace: IMarketplace): Observable<EntityResponseType> {
        return this.http.put<IMarketplace>(this.resourceUrl, marketplace, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMarketplace>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMarketplace[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
