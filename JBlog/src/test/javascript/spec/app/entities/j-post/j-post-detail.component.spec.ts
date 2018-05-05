/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JBlogTestModule } from '../../../test.module';
import { JPostDetailComponent } from '../../../../../../main/webapp/app/entities/j-post/j-post-detail.component';
import { JPostService } from '../../../../../../main/webapp/app/entities/j-post/j-post.service';
import { JPost } from '../../../../../../main/webapp/app/entities/j-post/j-post.model';

describe('Component Tests', () => {

    describe('JPost Management Detail Component', () => {
        let comp: JPostDetailComponent;
        let fixture: ComponentFixture<JPostDetailComponent>;
        let service: JPostService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JBlogTestModule],
                declarations: [JPostDetailComponent],
                providers: [
                    JPostService
                ]
            })
            .overrideTemplate(JPostDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JPostDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JPostService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new JPost(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.jPost).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
