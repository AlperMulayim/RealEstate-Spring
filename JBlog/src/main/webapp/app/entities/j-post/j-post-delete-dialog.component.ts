import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { JPost } from './j-post.model';
import { JPostPopupService } from './j-post-popup.service';
import { JPostService } from './j-post.service';

@Component({
    selector: 'jhi-j-post-delete-dialog',
    templateUrl: './j-post-delete-dialog.component.html'
})
export class JPostDeleteDialogComponent {

    jPost: JPost;

    constructor(
        private jPostService: JPostService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jPostService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'jPostListModification',
                content: 'Deleted an jPost'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-j-post-delete-popup',
    template: ''
})
export class JPostDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private jPostPopupService: JPostPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.jPostPopupService
                .open(JPostDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
