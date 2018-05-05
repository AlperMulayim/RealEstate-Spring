import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { JPost } from './j-post.model';
import { JPostService } from './j-post.service';

@Component({
    selector: 'jhi-j-post-detail',
    templateUrl: './j-post-detail.component.html'
})
export class JPostDetailComponent implements OnInit, OnDestroy {

    jPost: JPost;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private jPostService: JPostService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInJPosts();
    }

    load(id) {
        this.jPostService.find(id)
            .subscribe((jPostResponse: HttpResponse<JPost>) => {
                this.jPost = jPostResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInJPosts() {
        this.eventSubscriber = this.eventManager.subscribe(
            'jPostListModification',
            (response) => this.load(this.jPost.id)
        );
    }
}
