import { IMarketplace } from 'app/shared/model//marketplace.model';
import { ISolution } from 'app/shared/model//solution.model';
import { IRecipe } from 'app/shared/model//recipe.model';

export interface IProducer {
    id?: number;
    name?: string;
    email?: string;
    marketplace?: IMarketplace;
    solutions?: ISolution[];
    recipes?: IRecipe[];
}

export class Producer implements IProducer {
    constructor(
        public id?: number,
        public name?: string,
        public email?: string,
        public marketplace?: IMarketplace,
        public solutions?: ISolution[],
        public recipes?: IRecipe[]
    ) {}
}
