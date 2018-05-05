import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { JPost } from './j-post.model';
import { JPostPopupService } from './j-post-popup.service';
import { JPostService } from './j-post.service';

@Component({
    selector: 'jhi-j-post-dialog',
    templateUrl: './j-post-dialog.component.html'
})
export class JPostDialogComponent implements OnInit {

    jPost: JPost;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jPostService: JPostService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.jPost.id !== undefined) {
            this.subscribeToSaveResponse(
                this.jPostService.update(this.jPost));
        } else {
            this.subscribeToSaveResponse(
                this.jPostService.create(this.jPost));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<JPost>>) {
        result.subscribe((res: HttpResponse<JPost>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: JPost) {
        this.eventManager.broadcast({ name: 'jPostListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-j-post-popup',
    template: ''
})
export class JPostPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private jPostPopupService: JPostPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.jPostPopupService
                    .open(JPostDialogComponent as Component, params['id']);
            } else {
                this.jPostPopupService
                    .open(JPostDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
