import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JPost } from './j-post.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<JPost>;

@Injectable()
export class JPostService {

    private resourceUrl =  SERVER_API_URL + 'api/j-posts';

    constructor(private http: HttpClient) { }

    create(jPost: JPost): Observable<EntityResponseType> {
        const copy = this.convert(jPost);
        return this.http.post<JPost>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(jPost: JPost): Observable<EntityResponseType> {
        const copy = this.convert(jPost);
        return this.http.put<JPost>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<JPost>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<JPost[]>> {
        const options = createRequestOption(req);
        return this.http.get<JPost[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<JPost[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: JPost = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<JPost[]>): HttpResponse<JPost[]> {
        const jsonResponse: JPost[] = res.body;
        const body: JPost[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to JPost.
     */
    private convertItemFromServer(jPost: JPost): JPost {
        const copy: JPost = Object.assign({}, jPost);
        return copy;
    }

    /**
     * Convert a JPost to a JSON which can be sent to the server.
     */
    private convert(jPost: JPost): JPost {
        const copy: JPost = Object.assign({}, jPost);
        return copy;
    }
}
