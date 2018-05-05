import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { JPost } from './j-post.model';
import { JPostService } from './j-post.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-j-post',
    templateUrl: './j-post.component.html'
})
export class JPostComponent implements OnInit, OnDestroy {
jPosts: JPost[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jPostService: JPostService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.jPostService.query().subscribe(
            (res: HttpResponse<JPost[]>) => {
                this.jPosts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInJPosts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: JPost) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInJPosts() {
        this.eventSubscriber = this.eventManager.subscribe('jPostListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
