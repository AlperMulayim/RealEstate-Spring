import { Component, OnInit } from '@angular/core';
import {JPost} from "../entities/j-post/j-post.model";
import {JPostService} from "../entities/j-post/j-post.service";
import {HttpResponse} from "@angular/common/http";


@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})
export class HomeComponent implements OnInit {

    posts: JPost[] =[];

    constructor(private postService: JPostService){}


    ngOnInit(){
        this.loadAll();

    }
    loadAll(){
        this.postService.query().subscribe(
            (res:  HttpResponse<JPost[]>)  => {
                console.log(res.body);
                this.posts = res.body;
            },
            (error)=>{
                console.log(error);
            }
        );
    }

}
