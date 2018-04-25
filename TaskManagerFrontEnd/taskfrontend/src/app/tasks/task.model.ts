export class Task{
    public id: number;
    public name: string;
    public complated : boolean;
    public dueDate : string;


    constructor(id:number, name:string, complated:boolean, dueDate:string) {
        this.id = id;
        this.name = name;
        this.complated = complated;
        this.dueDate = dueDate;
    }

}
