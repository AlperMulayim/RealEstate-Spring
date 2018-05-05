/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JBlogTestModule } from '../../../test.module';
import { JPostComponent } from '../../../../../../main/webapp/app/entities/j-post/j-post.component';
import { JPostService } from '../../../../../../main/webapp/app/entities/j-post/j-post.service';
import { JPost } from '../../../../../../main/webapp/app/entities/j-post/j-post.model';

describe('Component Tests', () => {

    describe('JPost Management Component', () => {
        let comp: JPostComponent;
        let fixture: ComponentFixture<JPostComponent>;
        let service: JPostService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JBlogTestModule],
                declarations: [JPostComponent],
                providers: [
                    JPostService
                ]
            })
            .overrideTemplate(JPostComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JPostComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JPostService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new JPost(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.jPosts[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
