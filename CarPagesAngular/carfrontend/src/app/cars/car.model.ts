export class Car{

  public id: number;
  public model: string;
  public plaka: string;
  public driverName: string;
  public modelYear: number;

  constructor(id: number, model: string, plake: string, driverName: string, modelYear: number) {
    this.id = id;
    this.model = model;
    this.plaka = plake;
    this.driverName = driverName;
    this.modelYear = modelYear;
  }
}
