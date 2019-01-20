import { IProducer } from 'app/shared/model//producer.model';

export interface IRecipe {
    id?: number;
    name?: string;
    producer?: IProducer;
}

export class Recipe implements IRecipe {
    constructor(public id?: number, public name?: string, public producer?: IProducer) {}
}
