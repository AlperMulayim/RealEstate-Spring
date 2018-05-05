import { BaseEntity } from './../../shared';

export class JPost implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public body?: any,
        public author?: string,
    ) {
    }
}
